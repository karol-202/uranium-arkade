package pl.karol202.uranium.webcanvas.component.primitives

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.draw.drawComponent
import pl.karol202.uranium.webcanvas.values.PathFillRule
import pl.karol202.uranium.webcanvas.values.FillStyle
import pl.karol202.uranium.webcanvas.values.Path

fun WCRenderScope.pathFill(key: Any = AutoKey,
                           path: Path,
                           fillRule: PathFillRule? = null,
                           fillStyle: FillStyle) =
		drawComponent(key = key) {
			setFillStyle(fillStyle)
			if(fillRule != null) fill(path, fillRule) else fill(path)
		}
