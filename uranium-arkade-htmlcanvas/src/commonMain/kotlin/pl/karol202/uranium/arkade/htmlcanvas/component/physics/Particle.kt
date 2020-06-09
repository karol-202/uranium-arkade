package pl.karol202.uranium.arkade.htmlcanvas.component.physics

import pl.karol202.uranium.arkade.htmlcanvas.ArkadeElement
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderBuilder
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.component.base.ArkadeAbstractComponent
import pl.karol202.uranium.arkade.htmlcanvas.component.containers.translate
import pl.karol202.uranium.arkade.htmlcanvas.physics.PhysicsBody
import pl.karol202.uranium.arkade.htmlcanvas.physics.PhysicsContext
import pl.karol202.uranium.arkade.htmlcanvas.values.Vector
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.render

class Particle(props: Props) : ArkadeAbstractComponent<Particle.Props>(props)
{
	data class Props(override val key: Any,
	                 val state: State,
	                 val mass: Double,
	                 val onStateChange: (State) -> Unit,
	                 val content: List<ArkadeElement<*>>) : UProps

	data class State(val position: Vector,
	                 val velocity: Vector)

	private val state get() = props.state
	private val body get() = PhysicsBody(Vector.ZERO, state.velocity, props.mass)

	override fun ArkadeRenderBuilder.render()
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

fun ArkadeRenderScope.particle(key: Any = AutoKey,
                               state: Particle.State,
                               mass: Double,
                               onStateChange: (Particle.State) -> Unit,
                               content: ArkadeRenderBuilder.() -> Unit) =
		component(::Particle, Particle.Props(key, state, mass, onStateChange, content.render()))
