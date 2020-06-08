package pl.karol202.uranium.arkade.htmlcanvas.component.primitives

import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.component.draw.drawComponent
import pl.karol202.uranium.arkade.htmlcanvas.values.Bounds
import pl.karol202.uranium.core.common.AutoKey

fun ArkadeRenderScope.rectClear(key: Any = AutoKey,
                                bounds: Bounds) =
		drawComponent(key = key) {
			clearRect(bounds.start, bounds.size)
		}
