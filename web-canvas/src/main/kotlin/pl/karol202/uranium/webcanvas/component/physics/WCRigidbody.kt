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
	                 val content: List<WCElement<*>>) : UProps

	data class State(val position: Vector,
	                 val velocity: Vector) : UState

	override var state by state(State(props.initialPosition, props.initialVelocity))

	private val body get() = PhysicsBody(Vector.ZERO, props.mass)

	override fun WCRenderBuilder.render()
	{
		+ translate(vector = state.position) {
			+ physicsPerformer { performPhysics() }
			+ collider(collider = props.collider)
			+ props.content
		}
	}

	private fun PhysicsContext.performPhysics()
	{
		val force = getForceAt(body)
		val acceleration = force / props.mass ?: return
		val oldVelocity = state.velocity
		val newVelocity = oldVelocity + (acceleration * deltaTime.inSeconds)
		val offset = listOf(oldVelocity, newVelocity).average() * deltaTime.inSeconds
		val newState = state.copy(position = state.position + offset, velocity = newVelocity)
		state = resolveCollisions(offset, newState)
	}

	private fun PhysicsContext.resolveCollisions(moveOffset: Vector, state: State) =
			checkForCollisions(props.collider).fold(state) { current, collision ->
				current.copy(position = current.position + collision.penetration)
			}
}

fun WCRenderScope.rigidbody(key: Any = AutoKey,
                            initialPosition: Vector,
                            initialVelocity: Vector,
                            mass: Double,
                            collider: Collider,
                            content: WCRenderBuilder.() -> Unit) =
		component(::WCRigidbody, WCRigidbody.Props(key, initialPosition, initialVelocity, mass, collider, content.render()))
