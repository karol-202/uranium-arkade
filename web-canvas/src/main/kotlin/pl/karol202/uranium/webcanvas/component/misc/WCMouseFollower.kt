package pl.karol202.uranium.webcanvas.component.misc

import pl.karol202.uranium.core.common.*
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.URenderScope
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.webcanvas.*
import pl.karol202.uranium.webcanvas.component.base.WCAbstractComponent
import pl.karol202.uranium.webcanvas.component.containers.translate
import pl.karol202.uranium.webcanvas.component.draw.drawContainer
import pl.karol202.uranium.webcanvas.component.event.eventHandler
import pl.karol202.uranium.webcanvas.component.event.eventTransformer
import pl.karol202.uranium.webcanvas.draw.DrawContext
import pl.karol202.uranium.webcanvas.values.InputEvent
import pl.karol202.uranium.webcanvas.values.Vector
import pl.karol202.uranium.webcanvas.values.clamp
import kotlin.math.min

class WCMouseFollower(props: Props) : WCAbstractComponent<WCMouseFollower.Props>(props),
                                      UStateful<WCMouseFollower.State>
{
	data class Props(override val key: Any,
	                 val minX: Double,
	                 val maxX: Double,
	                 val minY: Double,
	                 val maxY: Double,
	                 val content: List<WCElement<*>>) : UProps

	data class State(val position: Vector = Vector.ZERO) : UState

	override var state by state(State())

	override fun URenderBuilder<WC>.render()
	{
		+ eventHandler { handleEvent(it) }
		+ translate(vector = state.position) { + props.content }
	}

	private fun handleEvent(event: InputEvent)
	{
		if(event is InputEvent.Mouse.Move) setState { copy(position = limitPosition(event.location)) }
	}

	private fun limitPosition(position: Vector) =
			Vector(clamp(position.x, props.minX, props.maxX), clamp(position.y, props.minY, props.maxY))
}

fun WCRenderScope.mouseFollower(key: Any = AutoKey,
                                minX: Double = Double.NEGATIVE_INFINITY,
                                maxX: Double = Double.POSITIVE_INFINITY,
                                minY: Double = Double.NEGATIVE_INFINITY,
                                maxY: Double = Double.POSITIVE_INFINITY,
                                content: WCRenderBuilder.() -> Unit) =
		component(::WCMouseFollower, WCMouseFollower.Props(key, minX, maxX, minY, maxY, content.render()))
