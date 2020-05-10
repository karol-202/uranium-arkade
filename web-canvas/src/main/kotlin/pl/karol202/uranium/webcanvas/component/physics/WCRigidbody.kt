package pl.karol202.uranium.webcanvas.component.physics

import pl.karol202.uranium.core.common.*
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.webcanvas.WCElement
import pl.karol202.uranium.webcanvas.WCRenderBuilder
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.base.WCAbstractComponent
import pl.karol202.uranium.webcanvas.component.containers.translate
import pl.karol202.uranium.webcanvas.component.physics.collider.collider
import pl.karol202.uranium.webcanvas.physics.Collision
import pl.karol202.uranium.webcanvas.physics.PhysicsBody
import pl.karol202.uranium.webcanvas.physics.PhysicsContext
import pl.karol202.uranium.webcanvas.physics.collider.Collider
import pl.karol202.uranium.webcanvas.values.Vector
import pl.karol202.uranium.webcanvas.values.average

class WCRigidbody(props: Props) : WCAbstractComponent<WCRigidbody.Props>(props),
                                  UStateful<WCRigidbody.State>
{
	data class Props(override val key: Any,
	                 val initialPosition: Vector,
	                 val initialVelocity: Vector,
	                 val mass: Double,
	                 val collider: Collider,
	                 val onCollision: (State.(Collision) -> State)?,
	                 val content: List<WCElement<*>>) : UProps

	data class State(val position: Vector,
	                 val velocity: Vector) : UState
	{
		fun bounce(normal: Vector, factor: Double = 1.0) = copy(velocity = bouncedVelocity(normal) * factor)

		// Law of reflection formula (inverted velocity)
		private fun bouncedVelocity(normal: Vector) = normal * 2.0 * (normal dot -velocity) + velocity
	}

	override var state by state(State(props.initialPosition, props.initialVelocity))

	private val body get() = PhysicsBody(Vector.ZERO, state.velocity, props.mass)

	override fun WCRenderBuilder.render()
	{
		+ translate(vector = state.position) {
			+ physicsPerformer { performPhysics(this) }
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
		state = newContext.resolveCollisions(newState)
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
                            initialPosition: Vector,
                            initialVelocity: Vector,
                            mass: Double,
                            collider: Collider,
                            onCollision: (WCRigidbody.State.(Collision) -> WCRigidbody.State)?,
                            content: WCRenderBuilder.() -> Unit) =
		component(::WCRigidbody, WCRigidbody.Props(key, initialPosition, initialVelocity,
		                                           mass, collider, onCollision, content.render()))
