package pl.karol202.uranium.arkade.canvas.component.physics

import pl.karol202.uranium.arkade.canvas.WCElement
import pl.karol202.uranium.arkade.canvas.WCRenderBuilder
import pl.karol202.uranium.arkade.canvas.WCRenderScope
import pl.karol202.uranium.arkade.canvas.component.base.WCAbstractComponent
import pl.karol202.uranium.arkade.canvas.component.containers.translate
import pl.karol202.uranium.arkade.canvas.component.physics.collider.collider
import pl.karol202.uranium.arkade.canvas.physics.Collision
import pl.karol202.uranium.arkade.canvas.physics.PhysicsBody
import pl.karol202.uranium.arkade.canvas.physics.PhysicsContext
import pl.karol202.uranium.arkade.canvas.physics.collider.Collider
import pl.karol202.uranium.arkade.canvas.values.Vector
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.render

class WCRigidbody(props: Props) : WCAbstractComponent<WCRigidbody.Props>(props)
{
	data class Props(override val key: Any,
	                 val state: State,
	                 val mass: Double,
	                 val collider: Collider,
	                 val onStateChange: (State) -> Unit,
	                 val onCollision: (State.(Collision) -> State)?,
	                 val content: List<WCElement<*>>) : UProps

	data class State(val position: Vector,
	                 val velocity: Vector)
	{
		fun bounce(normal: Vector, factor: Double = 1.0) = copy(velocity = bouncedVelocity(normal) * factor)

		// Law of reflection formula (inverted velocity)
		private fun bouncedVelocity(normal: Vector) = normal * 2.0 * (normal dot -velocity) + velocity
	}

	private val state get() = props.state
	private val body get() = PhysicsBody(Vector.ZERO, state.velocity, props.mass)

	override fun WCRenderBuilder.render()
	{
		+ translate(vector = state.position) {
			+ physicsPerformer { performPhysics(it) }
			+ collider(collider = props.collider)
			+ props.content
		}
	}

	private fun performPhysics(context: PhysicsContext)
	{
		val newBody = context.processBody(body)
		val offset = newBody.position
		val newState = state.copy(position = state.position + offset, velocity = newBody.velocity)
		val newContext = context.excludeCollider(props.collider).translate(-offset)
		props.onStateChange(newContext.resolveCollisions(newState))
	}

	private fun PhysicsContext.resolveCollisions(state: State) =
			checkForCollisions(props.collider).fold(state) { current, collision -> current.resolveCollision(collision) }

	private fun State.resolveCollision(collision: Collision): State
	{
		val resolved = copy(position = position + collision.penetration)
		return props.onCollision?.let { resolved.it(collision) } ?: resolved
	}
}

fun WCRenderScope.rigidbody(key: Any = AutoKey,
                            state: WCRigidbody.State,
                            mass: Double,
                            collider: Collider,
                            onStateChange: (WCRigidbody.State) -> Unit,
                            onCollision: (WCRigidbody.State.(Collision) -> WCRigidbody.State)? = null,
                            content: WCRenderBuilder.() -> Unit) =
		component(::WCRigidbody, WCRigidbody.Props(key, state, mass, collider, onStateChange, onCollision, content.render()))
