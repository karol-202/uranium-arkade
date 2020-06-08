package pl.karol202.uranium.arkade.canvas.component.physics

import pl.karol202.uranium.arkade.canvas.WCElement
import pl.karol202.uranium.arkade.canvas.WCRenderBuilder
import pl.karol202.uranium.arkade.canvas.WCRenderScope
import pl.karol202.uranium.arkade.canvas.component.base.WCAbstractComponent
import pl.karol202.uranium.arkade.canvas.component.containers.translate
import pl.karol202.uranium.arkade.canvas.physics.PhysicsBody
import pl.karol202.uranium.arkade.canvas.physics.PhysicsContext
import pl.karol202.uranium.arkade.canvas.values.Vector
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.render

class WCParticle(props: Props) : WCAbstractComponent<WCParticle.Props>(props)
{
	data class Props(override val key: Any,
	                 val state: State,
	                 val mass: Double,
	                 val onStateChange: (State) -> Unit,
	                 val content: List<WCElement<*>>) : UProps

	data class State(val position: Vector,
	                 val velocity: Vector)

	private val state get() = props.state
	private val body get() = PhysicsBody(Vector.ZERO, state.velocity, props.mass)

	override fun WCRenderBuilder.render()
	{
		+ translate(vector = state.position) {
			+ physicsPerformer { performPhysics(it) }
			+ props.content
		}
	}

	private fun performPhysics(context: PhysicsContext)
	{
		val newBody = context.processBody(body)
		props.onStateChange(state.copy(position = state.position + newBody.position, velocity = newBody.velocity))
	}
}

fun WCRenderScope.particle(key: Any = AutoKey,
                           state: WCParticle.State,
                           mass: Double,
                           onStateChange: (WCParticle.State) -> Unit,
                           content: WCRenderBuilder.() -> Unit) =
		component(::WCParticle, WCParticle.Props(key, state, mass, onStateChange, content.render()))
