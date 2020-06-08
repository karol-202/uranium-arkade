package pl.karol202.uranium.arkade.htmlcanvas.sample.js

import pl.karol202.uranium.arkade.htmlcanvas.Arkade
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeElement
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderBuilder
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.component.base.AbstractComponent
import pl.karol202.uranium.arkade.htmlcanvas.component.containers.translate
import pl.karol202.uranium.arkade.htmlcanvas.component.event.eventHandler
import pl.karol202.uranium.arkade.htmlcanvas.values.InputEvent
import pl.karol202.uranium.arkade.htmlcanvas.values.InputEvent.Mouse.Type
import pl.karol202.uranium.arkade.htmlcanvas.values.Vector
import pl.karol202.uranium.core.common.*
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.render

class MouseFollower(props: Props) : AbstractComponent<MouseFollower.Props>(props),
                                    UStateful<MouseFollower.State>
{
	data class Props(override val key: Any,
	                 val minX: Double,
	                 val maxX: Double,
	                 val minY: Double,
	                 val maxY: Double,
	                 val initialPosition: Vector,
	                 val content: List<ArkadeElement<*>>) : UProps

	data class State(val position: Vector) : UState

	override var state by state(State(props.initialPosition))

	override fun URenderBuilder<Arkade>.render()
	{
		+ eventHandler(mouseListener = { handleEvent(it) })
		+ translate(vector = state.position) { + props.content }
	}

	private fun handleEvent(event: InputEvent.Mouse)
	{
		if(event.type == Type.MOVE) setState { copy(position = limitPosition(event.location)) }
	}

	private fun limitPosition(position: Vector) =
			Vector(position.x.coerceIn(props.minX, props.maxX), position.y.coerceIn(props.minY, props.maxY))
}

fun ArkadeRenderScope.mouseFollower(key: Any = AutoKey,
                                    minX: Double = Double.NEGATIVE_INFINITY,
                                    maxX: Double = Double.POSITIVE_INFINITY,
                                    minY: Double = Double.NEGATIVE_INFINITY,
                                    maxY: Double = Double.POSITIVE_INFINITY,
                                    initialPosition: Vector = Vector.ZERO,
                                    content: ArkadeRenderBuilder.() -> Unit) =
		component(::MouseFollower, MouseFollower.Props(key, minX, maxX, minY, maxY, initialPosition, content.render()))
