package pl.karol202.uranium.webcanvas.component.primitives

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.draw.drawComponent
import pl.karol202.uranium.webcanvas.values.FillStyle
import pl.karol202.uranium.webcanvas.values.Path
import pl.karol202.uranium.webcanvas.values.PathFillRule

fun WCRenderScope.pathFill(key: Any = AutoKey,
                           path: Path,
                           fillRule: PathFillRule? = null,
                           fillStyle: FillStyle) =
		drawComponent(key = key) {
			this.fillStyle = fillStyle.createNativeStyle(this)
			if(fillRule != null) fill(path.nativePath, fillRule.native) else fill(path.nativePath)
		}
