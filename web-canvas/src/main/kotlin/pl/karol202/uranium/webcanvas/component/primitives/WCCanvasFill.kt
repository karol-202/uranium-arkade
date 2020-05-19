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
import pl.karol202.uranium.webcanvas.values.FillStyle

class WCCanvasFill(props: Props) : WCAbstractComponent<WCCanvasFill.Props>(props)
{
	data class Props(override val key: Any,
	                 val fillStyle: FillStyle) : UProps

	override fun URenderBuilder<WC>.render()
	{
		+ drawComponent {
			fillStyle = props.fillStyle.createNativeStyle(this)
			fillCanvas()
		}
	}
}

fun WCRenderScope.canvasFill(key: Any = AutoKey,
                             fillStyle: FillStyle) = component(::WCCanvasFill, WCCanvasFill.Props(key, fillStyle))
