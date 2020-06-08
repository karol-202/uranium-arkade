package pl.karol202.uranium.arkade.htmlcanvas.component.physics

import pl.karol202.uranium.arkade.htmlcanvas.ArkadeElement
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderBuilder
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.component.base.AbstractComponent
import pl.karol202.uranium.arkade.htmlcanvas.component.containers.translate
import pl.karol202.uranium.arkade.htmlcanvas.component.physics.collider.colliderProvider
import pl.karol202.uranium.arkade.htmlcanvas.physics.Collision
import pl.karol202.uranium.arkade.htmlcanvas.physics.PhysicsBody
import pl.karol202.uranium.arkade.htmlcanvas.physics.PhysicsContext
import pl.karol202.uranium.arkade.htmlcanvas.physics.collider.Collider
import pl.karol202.uranium.arkade.htmlcanvas.values.Vector
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.render

class Rigidbody(props: Props) : AbstractComponent<Rigidbody.Props>(props)
{
	data class Props(override val key: Any,
	                 val state: State,
	                 val mass: Double,
	                 val collider: Collider,
	                 val onStateChange: (State) -> Unit,
	                 val onCollision: (State.(Collision) -> State)?,
	                 val content: List<ArkadeElement<*>>) : UProps

	data class State(val position: Vector,
	                 val velocity: Vector)
	{
		fun bounce(normal: Vector, factor: Double = 1.0) = copy(velocity = bouncedVelocity(normal) * factor)

		// Law of reflection formula (inverted velocity)
		private fun bouncedVelocity(normal: Vector) = normal * 2.0 * (normal dot -velocity) + velocity
	}

	private val state get() = props.state
	private val body get() = PhysicsBody(Vector.ZERO, state.velocity, props.mass)

	override fun ArkadeRenderBuilder.render()
	{
		+ translate(vector = state.position) {
			+ physicsPerformer { performPhysics(it) }
			+ colliderProvider(collider = props.collider)
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

fun ArkadeRenderScope.rigidbody(key: Any = AutoKey,
                                state: Rigidbody.State,
                                mass: Double,
                                collider: Collider,
                                onStateChange: (Rigidbody.State) -> Unit,
                                onCollision: (Rigidbody.State.(Collision) -> Rigidbody.State)? = null,
                                content: ArkadeRenderBuilder.() -> Unit) =
		component(::Rigidbody, Rigidbody.Props(key, state, mass, collider, onStateChange, onCollision, content.render()))
