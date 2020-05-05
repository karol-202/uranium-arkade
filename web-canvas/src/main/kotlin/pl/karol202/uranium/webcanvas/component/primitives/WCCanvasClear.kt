package pl.karol202.uranium.webcanvas.component.primitives

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.base.WCAbstractComponent
import pl.karol202.uranium.webcanvas.component.draw.drawComponent
import pl.karol202.uranium.webcanvas.values.Bounds

class WCCanvasClear(props: Props) : WCAbstractComponent<WCCanvasClear.Props>(props)
{
	data class Props(override val key: Any) : UProps

	override fun URenderBuilder<WC>.render()
	{
		+ drawComponent {
			clearRect(0.0, 0.0, canvas.width.toDouble(), canvas.height.toDouble())
		}
	}
}

fun WCRenderScope.canvasClear(key: Any = AutoKey) = component(::WCCanvasClear, WCCanvasClear.Props(key))
