package pl.karol202.uranium.webcanvas.component.primitives

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.webcanvas.*
import pl.karol202.uranium.webcanvas.component.base.WCAbstractComponent
import pl.karol202.uranium.webcanvas.component.draw.drawComponent
import pl.karol202.uranium.webcanvas.values.Color
import pl.karol202.uranium.webcanvas.values.FillStyle
import pl.karol202.uranium.webcanvas.values.Vector
import kotlin.math.PI

class WCCircleFill(props: Props) : WCAbstractComponent<WCCircleFill.Props>(props)
{
	data class Props(override val key: Any,
	                 val center: Vector,
	                 val radius: Double,
	                 val startAngle: Double,
	                 val endAngle: Double,
	                 val fillStyle: FillStyle) : UProps

	override fun URenderBuilder<WC>.render()
	{
		+ drawComponent {
			fillStyle = props.fillStyle.createNativeStyle(this)
			beginPath()
			arc(props.center.x, props.center.y, props.radius, props.startAngle, props.endAngle, false)
			fill()
		}
	}
}

fun WCRenderScope.circleFill(key: Any = AutoKey,
                             center: Vector,
                             radius: Double,
                             startAngle: Double = 0.0,
                             endAngle: Double = 2 * PI,
                             fillStyle: FillStyle) =
		component(::WCCircleFill, WCCircleFill.Props(key, center, radius, startAngle, endAngle, fillStyle))
