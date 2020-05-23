package pl.karol202.uranium.webcanvas.component.primitives

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.draw.drawComponent
import pl.karol202.uranium.webcanvas.draw.fillCanvas
import pl.karol202.uranium.webcanvas.values.FillStyle

fun WCRenderScope.canvasFill(key: Any = AutoKey,
                             fillStyle: FillStyle) =
		drawComponent(key = key) {
			this.fillStyle = fillStyle.createNativeStyle(this)
			fillCanvas()
		}
