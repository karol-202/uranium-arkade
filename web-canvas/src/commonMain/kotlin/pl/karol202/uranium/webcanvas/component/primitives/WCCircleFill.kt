package pl.karol202.uranium.webcanvas.component.primitives

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.draw.drawComponent
import pl.karol202.uranium.webcanvas.values.FillStyle
import pl.karol202.uranium.webcanvas.values.Vector
import kotlin.math.PI

fun WCRenderScope.circleFill(key: Any = AutoKey,
                             center: Vector,
                             radius: Double,
                             startAngle: Double = 0.0,
                             endAngle: Double = 2 * PI,
                             fillStyle: FillStyle) =
		drawComponent(key = key) {
			this.fillStyle = fillStyle.createNativeStyle(this)
			beginPath()
			arc(center.x, center.y, radius, startAngle, endAngle, false)
			fill()
		}
