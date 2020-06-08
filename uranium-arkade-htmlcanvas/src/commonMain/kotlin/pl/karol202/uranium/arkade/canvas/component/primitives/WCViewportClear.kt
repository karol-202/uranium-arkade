package pl.karol202.uranium.arkade.canvas.component.primitives

import pl.karol202.uranium.arkade.canvas.WCRenderScope
import pl.karol202.uranium.arkade.canvas.component.draw.drawComponent
import pl.karol202.uranium.core.common.AutoKey

fun WCRenderScope.viewportClear(key: Any = AutoKey) = drawComponent(key = key) { clearViewport() }
