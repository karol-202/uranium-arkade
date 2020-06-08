package pl.karol202.uranium.arkade.htmlcanvas.component.primitives

import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.component.draw.drawComponent
import pl.karol202.uranium.core.common.AutoKey

fun ArkadeRenderScope.viewportClear(key: Any = AutoKey) = drawComponent(key = key) { clearViewport() }
