package pl.karol202.uranium.arkade.canvas.component.primitives

import pl.karol202.uranium.arkade.canvas.WCRenderScope
import pl.karol202.uranium.arkade.canvas.component.draw.drawComponent
import pl.karol202.uranium.arkade.canvas.values.Bounds
import pl.karol202.uranium.core.common.AutoKey

fun WCRenderScope.rectClear(key: Any = AutoKey,
                            bounds: Bounds) =
		drawComponent(key = key) {
			clearRect(bounds.start, bounds.size)
		}
