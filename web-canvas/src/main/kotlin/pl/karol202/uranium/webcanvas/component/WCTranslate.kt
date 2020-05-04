package pl.karol202.uranium.webcanvas.component

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderScope
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.webcanvas.*
import pl.karol202.uranium.webcanvas.native.WCNativeContainer
import pl.karol202.uranium.webcanvas.native.WCNativeLeaf
import pl.karol202.uranium.webcanvas.render.DrawContext
import pl.karol202.uranium.webcanvas.values.Bounds
import pl.karol202.uranium.webcanvas.values.Color
import pl.karol202.uranium.webcanvas.values.Vector

class WCTranslate(props: Props) : WCAbstractNativeComponent<WCTranslate.Props>(props)
{
	data class Props(override val key: Any,
	                 val vector: Vector,
	                 val content: List<WCElement<*>>) : UProps

	override val native = WCNativeContainer(beforeDrawOperation = { drawBefore() },
	                                        afterDrawOperation = { drawAfter() })

	override fun URenderScope<WC>.render() = props.content

	private fun DrawContext.drawBefore()
	{
		save()
		translate(props.vector.x, props.vector.y)
	}

	private fun DrawContext.drawAfter()
	{
		restore()
	}
}

fun WCRenderScope.translate(key: Any = AutoKey,
                            vector: Vector,
                            content: WCRenderBuilder.() -> Unit) =
		component(::WCTranslate, WCTranslate.Props(key, vector, content.render()))
