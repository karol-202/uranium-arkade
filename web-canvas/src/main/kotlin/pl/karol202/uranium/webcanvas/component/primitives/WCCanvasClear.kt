package pl.karol202.uranium.webcanvas.component.primitives

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.draw.drawComponent
import pl.karol202.uranium.webcanvas.draw.clearCanvas

fun WCRenderScope.canvasClear(key: Any = AutoKey) = drawComponent(key = key) { clearCanvas() }
