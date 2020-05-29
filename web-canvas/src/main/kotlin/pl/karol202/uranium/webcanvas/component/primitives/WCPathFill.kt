package pl.karol202.uranium.webcanvas.component.primitives

import org.w3c.dom.CanvasFillRule
import org.w3c.dom.EVENODD
import org.w3c.dom.NONZERO
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.base.WCAbstractComponent
import pl.karol202.uranium.webcanvas.component.draw.drawComponent
import pl.karol202.uranium.webcanvas.draw.DrawContext
import pl.karol202.uranium.webcanvas.values.FillStyle
import pl.karol202.uranium.webcanvas.values.Path

enum class PathFillRule(val native: CanvasFillRule)
{
	NONZERO(CanvasFillRule.NONZERO),
	EVENODD(CanvasFillRule.EVENODD)
}

fun WCRenderScope.pathFill(key: Any = AutoKey,
                           path: Path,
                           fillRule: PathFillRule? = null,
                           fillStyle: FillStyle) =
		drawComponent(key = key) {
			this.fillStyle = fillStyle.createNativeStyle(this)
			if(fillRule != null) fill(path.nativePath, fillRule.native) else fill(path.nativePath)
		}
