package pl.karol202.uranium.webcanvas.test

import pl.karol202.uranium.core.common.*
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.WCElement
import pl.karol202.uranium.webcanvas.WCRenderBuilder
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.base.WCAbstractComponent
import pl.karol202.uranium.webcanvas.component.containers.translate
import pl.karol202.uranium.webcanvas.component.event.eventHandler
import pl.karol202.uranium.webcanvas.input.InputEvent
import pl.karol202.uranium.webcanvas.input.InputEvent.Mouse.Type
import pl.karol202.uranium.webcanvas.values.Vector

class MouseFollower(props: Props) : WCAbstractComponent<MouseFollower.Props>(props),
                                    UStateful<MouseFollower.State>
{
	data class Props(override val key: Any,
	                 val minX: Double,
	                 val maxX: Double,
	                 val minY: Double,
	                 val maxY: Double,
	                 val initialPosition: Vector,
	                 val content: List<WCElement<*>>) : UProps

	data class State(val position: Vector) : UState

	override var state by state(State(props.initialPosition))

	override fun URenderBuilder<WC>.render()
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

fun WCRenderScope.mouseFollower(key: Any = AutoKey,
                                minX: Double = Double.NEGATIVE_INFINITY,
                                maxX: Double = Double.POSITIVE_INFINITY,
                                minY: Double = Double.NEGATIVE_INFINITY,
                                maxY: Double = Double.POSITIVE_INFINITY,
                                initialPosition: Vector = Vector.ZERO,
                                content: WCRenderBuilder.() -> Unit) =
		component(::MouseFollower, MouseFollower.Props(key, minX, maxX, minY, maxY, initialPosition, content.render()))
