package pl.karol202.uranium.arkade.canvas.component.primitives

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.arkade.canvas.WCRenderScope
import pl.karol202.uranium.arkade.canvas.component.draw.drawComponent
import pl.karol202.uranium.arkade.canvas.values.Bounds
import pl.karol202.uranium.arkade.canvas.values.FillStyle

fun WCRenderScope.rectFill(key: Any = AutoKey,
                           bounds: Bounds,
                           fillStyle: FillStyle) =
		drawComponent(key = key) {
			setFillStyle(fillStyle)
			fillRect(bounds.start, bounds.size)
		}
