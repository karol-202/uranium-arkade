package pl.karol202.uranium.webcanvas.component.primitives

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.base.WCAbstractComponent
import pl.karol202.uranium.webcanvas.component.draw.drawComponent
import pl.karol202.uranium.webcanvas.draw.DrawContext
import pl.karol202.uranium.webcanvas.draw.DrawOperation
import pl.karol202.uranium.webcanvas.values.Path

fun WCRenderScope.path(key: Any = AutoKey,
                       path: Path,
                       finishOperation: DrawOperation) =
		drawComponent(key = key) { drawPath(path = path,
		                                    finishOperation = finishOperation) }

private fun DrawContext.drawPath(path: Path,
                                 finishOperation: DrawOperation)
{
	if(path.points.size < 2) return
	beginPath()
	path.points.forEachIndexed { index, point ->
		if(index == 0) moveTo(point.x, point.y)
		else lineTo(point.x, point.y)
	}
	if(path.closed) closePath()
	finishOperation()
}
