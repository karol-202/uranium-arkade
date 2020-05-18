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

class WCPath(props: Props) : WCAbstractComponent<WCPath.Props>(props)
{
	data class Props(override val key: Any,
	                 val path: Path,
	                 val finishOperation: DrawOperation) : UProps

	private val points get() = props.path.points

	override fun URenderBuilder<WC>.render()
	{
		+ drawComponent { drawPath() }
	}

	private fun DrawContext.drawPath()
	{
		if(points.size < 2) return
		beginPath()
		points.forEachIndexed { index, point ->
			if(index == 0) moveTo(point.x, point.y)
			else lineTo(point.x, point.y)
		}
		props.finishOperation(this)
	}
}

fun WCRenderScope.path(key: Any = AutoKey,
                       path: Path,
                       finishOperation: DrawOperation) =
		component(::WCPath, WCPath.Props(key, path, finishOperation))
