package pl.karol202.uranium.arkade.canvas.component.primitives

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.arkade.canvas.WCRenderScope
import pl.karol202.uranium.arkade.canvas.component.draw.drawComponent
import pl.karol202.uranium.arkade.canvas.values.FillStyle

fun WCRenderScope.viewportFill(key: Any = AutoKey,
                               fillStyle: FillStyle) =
		drawComponent(key = key) {
			setFillStyle(fillStyle)
			fillViewport()
		}
