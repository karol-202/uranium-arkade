package pl.karol202.uranium.arkade.htmlcanvas.component.primitives

import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.component.draw.drawComponent
import pl.karol202.uranium.arkade.htmlcanvas.values.Bounds
import pl.karol202.uranium.arkade.htmlcanvas.values.FillStyle
import pl.karol202.uranium.core.common.AutoKey

fun ArkadeRenderScope.rectFill(key: Any = AutoKey,
                               bounds: Bounds,
                               fillStyle: FillStyle) =
		drawComponent(key = key) {
			setFillStyle(fillStyle)
			fillRect(bounds.start, bounds.size)
		}
