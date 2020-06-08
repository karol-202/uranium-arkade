package pl.karol202.uranium.arkade.htmlcanvas.component.primitives

import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.component.draw.drawComponent
import pl.karol202.uranium.arkade.htmlcanvas.values.FillStyle
import pl.karol202.uranium.arkade.htmlcanvas.values.Path
import pl.karol202.uranium.arkade.htmlcanvas.values.PathFillRule
import pl.karol202.uranium.core.common.AutoKey

fun ArkadeRenderScope.pathFill(key: Any = AutoKey,
                               path: Path,
                               fillRule: PathFillRule? = null,
                               fillStyle: FillStyle) =
		drawComponent(key = key) {
			setFillStyle(fillStyle)
			if(fillRule != null) fill(path, fillRule) else fill(path)
		}
