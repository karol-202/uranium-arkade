package pl.karol202.uranium.arkade.canvas.component.draw

import pl.karol202.uranium.arkade.canvas.WC
import pl.karol202.uranium.arkade.canvas.WCElement
import pl.karol202.uranium.arkade.canvas.WCRenderBuilder
import pl.karol202.uranium.arkade.canvas.WCRenderScope
import pl.karol202.uranium.arkade.canvas.component.base.WCAbstractNativeContainerComponent
import pl.karol202.uranium.arkade.canvas.draw.DrawContext
import pl.karol202.uranium.arkade.canvas.draw.DrawOperation
import pl.karol202.uranium.arkade.canvas.native.WCDrawNativeContainer
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.render

class WCDrawContainer(props: Props) : WCAbstractNativeContainerComponent<WCDrawContainer.Props>(props)
{
	data class Props(override val key: Any,
	                 val beforeDrawOperation: DrawOperation,
	                 val afterDrawOperation: DrawOperation,
	                 val content: List<WCElement<*>>) : UProps

	override val native = WCDrawNativeContainer(beforeDrawOperation = { beforeDraw() },
	                                            afterDrawOperation = { afterDraw() })

	override fun URenderBuilder<WC>.render() { + props.content }

	private fun DrawContext.beforeDraw() = props.beforeDrawOperation(this)

	private fun DrawContext.afterDraw() = props.afterDrawOperation(this)
}

fun WCRenderScope.drawContainer(key: Any = AutoKey,
                                beforeDrawOperation: DrawOperation,
                                afterDrawOperation: DrawOperation,
                                content: WCRenderBuilder.() -> Unit) =
		component(::WCDrawContainer, WCDrawContainer.Props(key, beforeDrawOperation, afterDrawOperation, content.render()))
