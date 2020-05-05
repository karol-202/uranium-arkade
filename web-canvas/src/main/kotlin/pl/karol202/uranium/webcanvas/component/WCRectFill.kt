package pl.karol202.uranium.webcanvas.component

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderScope
import pl.karol202.uranium.webcanvas.*
import pl.karol202.uranium.webcanvas.values.Bounds
import pl.karol202.uranium.webcanvas.values.Color

class WCRectFill(props: Props) : WCAbstractComponent<WCRectFill.Props>(props)
{
	data class Props(override val key: Any,
	                 val bounds: Bounds,
	                 val color: Color) : UProps

	override fun URenderScope<WC>.render() = drawComponent {
		fillStyle = props.color.asStyle
		fillRect(props.bounds.x, props.bounds.y, props.bounds.width, props.bounds.height)
	}.asList()
}

fun WCRenderScope.rectFill(key: Any = AutoKey,
                           bounds: Bounds,
                           style: Color) =
		component(::WCRectFill, WCRectFill.Props(key, bounds, style))
