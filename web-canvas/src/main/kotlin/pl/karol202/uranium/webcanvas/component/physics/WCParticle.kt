package pl.karol202.uranium.webcanvas.component.physics

import pl.karol202.uranium.core.common.*
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.webcanvas.WCElement
import pl.karol202.uranium.webcanvas.WCRenderBuilder
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.base.WCAbstractComponent
import pl.karol202.uranium.webcanvas.component.containers.translate
import pl.karol202.uranium.webcanvas.physics.PhysicsBody
import pl.karol202.uranium.webcanvas.physics.PhysicsContext
import pl.karol202.uranium.webcanvas.values.Vector
import pl.karol202.uranium.webcanvas.values.average

class WCParticle(props: Props) : WCAbstractComponent<WCParticle.Props>(props),
                                 UStateful<WCParticle.State>
{
	data class Props(override val key: Any,
	                 val initialPosition: Vector,
	                 val initialVelocity: Vector,
	                 val mass: Double,
	                 val content: List<WCElement<*>>) : UProps

	data class State(val position: Vector,
	                 val velocity: Vector) : UState

	override var state by state(State(props.initialPosition, props.initialVelocity))

	private val body get() = PhysicsBody(Vector.ZERO, state.velocity, props.mass)

	override fun WCRenderBuilder.render()
	{
		+ translate(vector = state.position) {
			+ physicsPerformer { performPhysics(this) }
			+ props.content
		}
	}

	private fun performPhysics(context: PhysicsContext)
	{
		val newBody = context.processBody(body)
		setState { copy(position = state.position + newBody.position, velocity = newBody.velocity) }
	}
}

fun WCRenderScope.particle(key: Any = AutoKey,
                           initialPosition: Vector,
                           initialVelocity: Vector,
                           mass: Double,
                           content: WCRenderBuilder.() -> Unit) =
		component(::WCParticle, WCParticle.Props(key, initialPosition, initialVelocity, mass, content.render()))
