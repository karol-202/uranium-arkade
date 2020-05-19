package pl.karol202.uranium.webcanvas.component.primitives

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.webcanvas.*
import pl.karol202.uranium.webcanvas.component.base.WCAbstractComponent
import pl.karol202.uranium.webcanvas.component.draw.drawComponent
import pl.karol202.uranium.webcanvas.values.Bounds
import pl.karol202.uranium.webcanvas.values.Color
import pl.karol202.uranium.webcanvas.values.FillStyle

class WCRectFill(props: Props) : WCAbstractComponent<WCRectFill.Props>(props)
{
	data class Props(override val key: Any,
	                 val bounds: Bounds,
	                 val fillStyle: FillStyle) : UProps

	private val bounds get() = props.bounds

	override fun URenderBuilder<WC>.render()
	{
		+ drawComponent {
			fillStyle = props.fillStyle.createNativeStyle(this)
			fillRect(bounds.x, bounds.y, bounds.width, bounds.height)
		}
	}
}

fun WCRenderScope.rectFill(key: Any = AutoKey,
                           bounds: Bounds,
                           fillStyle: FillStyle) =
		component(::WCRectFill, WCRectFill.Props(key, bounds, fillStyle))
