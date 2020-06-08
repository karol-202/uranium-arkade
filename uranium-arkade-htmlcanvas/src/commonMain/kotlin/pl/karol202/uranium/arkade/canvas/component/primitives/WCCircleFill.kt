package pl.karol202.uranium.arkade.canvas.component.primitives

import pl.karol202.uranium.arkade.canvas.WCRenderScope
import pl.karol202.uranium.arkade.canvas.component.draw.drawComponent
import pl.karol202.uranium.arkade.canvas.values.FillStyle
import pl.karol202.uranium.arkade.canvas.values.Vector
import pl.karol202.uranium.core.common.AutoKey
import kotlin.math.PI

fun WCRenderScope.circleFill(key: Any = AutoKey,
                             center: Vector,
                             radius: Double,
                             startAngle: Double = 0.0,
                             endAngle: Double = 2 * PI,
                             fillStyle: FillStyle) =
		drawComponent(key = key) {
			setFillStyle(fillStyle)
			beginPath()
			arc(center, radius, startAngle, endAngle)
			fill()
		}
