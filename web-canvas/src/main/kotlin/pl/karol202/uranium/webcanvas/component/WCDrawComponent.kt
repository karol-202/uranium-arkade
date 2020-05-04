package pl.karol202.uranium.webcanvas.component

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderScope
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.WCAbstractNativeComponent
import pl.karol202.uranium.webcanvas.WCElement
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.native.WCNativeLeaf
import pl.karol202.uranium.webcanvas.render.DrawOperation

class WCDrawComponent(props: Props) : WCAbstractNativeComponent<WCDrawComponent.Props>(props)
{
	data class Props(override val key: Any,
	                 val drawOperation: DrawOperation) : UProps

	override val native = WCNativeLeaf { props.drawOperation(this) }

	override fun URenderScope<WC>.render() = emptyList<WCElement<*>>()
}

fun WCRenderScope.drawComponent(key: Any = AutoKey,
                                drawOperation: DrawOperation) =
		component(::WCDrawComponent, WCDrawComponent.Props(key, drawOperation))
