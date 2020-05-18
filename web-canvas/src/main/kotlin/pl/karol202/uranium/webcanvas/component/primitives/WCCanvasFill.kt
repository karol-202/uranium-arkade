package pl.karol202.uranium.webcanvas.component.primitives

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.base.WCAbstractComponent
import pl.karol202.uranium.webcanvas.component.draw.drawComponent
import pl.karol202.uranium.webcanvas.draw.fillCanvas
import pl.karol202.uranium.webcanvas.values.Color

class WCCanvasFill(props: Props) : WCAbstractComponent<WCCanvasFill.Props>(props)
{
	data class Props(override val key: Any,
	                 val color: Color) : UProps

	override fun URenderBuilder<WC>.render()
	{
		+ drawComponent {
			fillStyle = props.color.asStyle
			fillCanvas()
		}
	}
}

fun WCRenderScope.canvasFill(key: Any = AutoKey,
                             color: Color) = component(::WCCanvasFill, WCCanvasFill.Props(key, color))
