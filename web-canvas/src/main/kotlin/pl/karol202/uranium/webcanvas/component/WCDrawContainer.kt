package pl.karol202.uranium.webcanvas.component

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderScope
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.WCAbstractNativeComponent
import pl.karol202.uranium.webcanvas.WCElement
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.native.WCNativeContainer
import pl.karol202.uranium.webcanvas.native.WCNativeLeaf
import pl.karol202.uranium.webcanvas.render.DrawOperation

class WCDrawContainer(props: Props) : WCAbstractNativeComponent<WCDrawContainer.Props>(props)
{
	data class Props(override val key: Any,
	                 val beforeDrawOperation: DrawOperation,
	                 val afterDrawOperation: DrawOperation,
	                 val content: List<WCElement<*>>) : UProps

	override val native = WCNativeContainer(beforeDrawOperation = props.beforeDrawOperation,
	                                        afterDrawOperation = props.afterDrawOperation)

	override fun URenderScope<WC>.render() = props.content
}

fun WCRenderScope.drawContainer(key: Any = AutoKey,
                                beforeDrawOperation: DrawOperation,
                                afterDrawOperation: DrawOperation,
                                content: List<WCElement<*>>) =
		component(::WCDrawContainer, WCDrawContainer.Props(key, beforeDrawOperation, afterDrawOperation, content))
