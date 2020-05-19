package pl.karol202.uranium.webcanvas.component.primitives

import org.w3c.dom.CanvasFillRule
import org.w3c.dom.EVENODD
import org.w3c.dom.NONZERO
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.base.WCAbstractComponent
import pl.karol202.uranium.webcanvas.draw.DrawContext
import pl.karol202.uranium.webcanvas.values.Color
import pl.karol202.uranium.webcanvas.values.FillStyle
import pl.karol202.uranium.webcanvas.values.Path

class WCPathFill(props: Props) : WCAbstractComponent<WCPathFill.Props>(props)
{
	data class Props(override val key: Any,
	                 val path: Path,
	                 val fillRule: FillRule?,
	                 val fillStyle: FillStyle) : UProps

	enum class FillRule(val native: CanvasFillRule)
	{
		NONZERO(CanvasFillRule.NONZERO),
		EVENODD(CanvasFillRule.EVENODD)
	}

	override fun URenderBuilder<WC>.render()
	{
		+ path(path = props.path,
		       finishOperation = { finish() })
	}

	private fun DrawContext.finish()
	{
		fillStyle = props.fillStyle.createNativeStyle(this)
		fillPath()
	}

	private fun DrawContext.fillPath() = props.fillRule?.let { fill(it.native) } ?: fill()
}

fun WCRenderScope.pathFill(key: Any = AutoKey,
                           path: Path,
                           fillRule: WCPathFill.FillRule? = null,
                           fillStyle: FillStyle) =
		component(::WCPathFill, WCPathFill.Props(key, path, fillRule, fillStyle))
