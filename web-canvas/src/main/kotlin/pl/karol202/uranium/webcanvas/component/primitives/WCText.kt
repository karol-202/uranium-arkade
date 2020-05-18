package pl.karol202.uranium.webcanvas.component.primitives

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.base.WCAbstractComponent
import pl.karol202.uranium.webcanvas.component.draw.drawComponent
import pl.karol202.uranium.webcanvas.values.Color
import pl.karol202.uranium.webcanvas.values.Font
import pl.karol202.uranium.webcanvas.values.Vector

class WCText(props: Props) : WCAbstractComponent<WCText.Props>(props)
{
	data class Props(override val key: Any,
	                 val position: Vector,
	                 val text: String,
	                 val font: Font,
	                 val color: Color) : UProps

	private val x get() = props.position.x
	private val y get() = props.position.y

	override fun URenderBuilder<WC>.render()
	{
		+ drawComponent {
			font = props.font.asText
			fillStyle = props.color.asStyle
			fillText(props.text, x, y)
		}
	}
}

fun WCRenderScope.text(key: Any = AutoKey,
                       position: Vector,
                       text: String,
                       font: Font,
                       color: Color) =
		component(::WCText, WCText.Props(key, position, text, font, color))
