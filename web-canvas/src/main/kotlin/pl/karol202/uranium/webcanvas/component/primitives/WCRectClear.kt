package pl.karol202.uranium.webcanvas.component.primitives

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.draw.drawComponent
import pl.karol202.uranium.webcanvas.values.Bounds

fun WCRenderScope.rectClear(key: Any = AutoKey,
                            bounds: Bounds) =
		drawComponent(key = key) {
			clearRect(bounds.x, bounds.y, bounds.width, bounds.height)
		}
