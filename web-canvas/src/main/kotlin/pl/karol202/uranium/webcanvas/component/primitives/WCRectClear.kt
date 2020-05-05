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

class WCRectClear(props: Props) : WCAbstractComponent<WCRectClear.Props>(props)
{
	data class Props(override val key: Any,
	                 val bounds: Bounds) : UProps

	private val bounds get() = props.bounds

	override fun URenderBuilder<WC>.render()
	{
		+ drawComponent {
			clearRect(bounds.x, bounds.y, bounds.width, bounds.height)
		}
	}
}

fun WCRenderScope.rectClear(key: Any = AutoKey,
                            bounds: Bounds) =
		component(::WCRectClear, WCRectClear.Props(key, bounds))
