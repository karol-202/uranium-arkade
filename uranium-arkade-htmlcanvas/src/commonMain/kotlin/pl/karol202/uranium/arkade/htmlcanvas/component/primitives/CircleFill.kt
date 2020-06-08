package pl.karol202.uranium.arkade.htmlcanvas.component.primitives

import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.component.draw.drawComponent
import pl.karol202.uranium.arkade.htmlcanvas.values.FillStyle
import pl.karol202.uranium.arkade.htmlcanvas.values.Vector
import pl.karol202.uranium.core.common.AutoKey
import kotlin.math.PI

fun ArkadeRenderScope.circleFill(key: Any = AutoKey,
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
