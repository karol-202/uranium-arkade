package pl.karol202.uranium.webcanvas.component.draw

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.URenderScope
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.WCElement
import pl.karol202.uranium.webcanvas.WCRenderBuilder
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.base.WCAbstractNativeContainerComponent
import pl.karol202.uranium.webcanvas.draw.DrawOperation
import pl.karol202.uranium.webcanvas.native.WCDrawNativeContainer

class WCDrawContainer(props: Props) : WCAbstractNativeContainerComponent<WCDrawContainer.Props>(props)
{
	data class Props(override val key: Any,
	                 val beforeDrawOperation: DrawOperation,
	                 val afterDrawOperation: DrawOperation,
	                 val content: List<WCElement<*>>) : UProps

	override val native = WCDrawNativeContainer(beforeDrawOperation = { props.beforeDrawOperation(this) },
	                                            afterDrawOperation = { props.afterDrawOperation(this) })

	override fun URenderBuilder<WC>.render() { + props.content }
}

fun WCRenderScope.drawContainer(key: Any = AutoKey,
                                beforeDrawOperation: DrawOperation,
                                afterDrawOperation: DrawOperation,
                                content: WCRenderBuilder.() -> Unit) =
		component(::WCDrawContainer, WCDrawContainer.Props(key, beforeDrawOperation, afterDrawOperation, content.render()))
