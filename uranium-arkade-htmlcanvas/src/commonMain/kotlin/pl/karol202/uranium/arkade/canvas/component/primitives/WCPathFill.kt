package pl.karol202.uranium.arkade.canvas.component.primitives

import pl.karol202.uranium.arkade.canvas.WCRenderScope
import pl.karol202.uranium.arkade.canvas.component.draw.drawComponent
import pl.karol202.uranium.arkade.canvas.values.FillStyle
import pl.karol202.uranium.arkade.canvas.values.Path
import pl.karol202.uranium.arkade.canvas.values.PathFillRule
import pl.karol202.uranium.core.common.AutoKey

fun WCRenderScope.pathFill(key: Any = AutoKey,
                           path: Path,
                           fillRule: PathFillRule? = null,
                           fillStyle: FillStyle) =
		drawComponent(key = key) {
			setFillStyle(fillStyle)
			if(fillRule != null) fill(path, fillRule) else fill(path)
		}
