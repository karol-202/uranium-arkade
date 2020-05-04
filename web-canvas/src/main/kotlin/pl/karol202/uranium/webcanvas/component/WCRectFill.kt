package pl.karol202.uranium.webcanvas.component

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderScope
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.WCAbstractNativeComponent
import pl.karol202.uranium.webcanvas.WCElement
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.native.WCNativeLeaf
import pl.karol202.uranium.webcanvas.render.DrawContext
import pl.karol202.uranium.webcanvas.values.Bounds
import pl.karol202.uranium.webcanvas.values.Color

class WCRectFill(props: Props) : WCAbstractNativeComponent<WCRectFill.Props>(props)
{
	data class Props(override val key: Any,
	                 val bounds: Bounds,
	                 val color: Color) : UProps

	private val x get() = props.bounds.start.x
	private val y get() = props.bounds.start.y
	private val width get() = props.bounds.size.x
	private val height get() = props.bounds.size.y

	override val native = WCNativeLeaf { draw() }

	override fun URenderScope<WC>.render() = emptyList<WCElement<*>>()

	private fun DrawContext.draw()
	{
		fillStyle = props.color.asStyle
		fillRect(x, y, width, height)
	}
}

fun WCRenderScope.rectFill(key: Any = AutoKey,
                           bounds: Bounds,
                           style: Color) = component(::WCRectFill, WCRectFill.Props(key, bounds, style))
