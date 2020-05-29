package pl.karol202.uranium.webcanvas.component.draw

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.base.WCAbstractNativeLeafComponent
import pl.karol202.uranium.webcanvas.values.DrawContext
import pl.karol202.uranium.webcanvas.native.WCDrawNativeLeaf
import pl.karol202.uranium.webcanvas.values.DrawOperation

class WCDrawComponent(props: Props) : WCAbstractNativeLeafComponent<WCDrawComponent.Props>(props)
{
	data class Props(override val key: Any,
	                 val drawOperation: DrawOperation) : UProps

	override val native = WCDrawNativeLeaf { draw() }

	private fun DrawContext.draw() = props.drawOperation(this)
}

fun WCRenderScope.drawComponent(key: Any = AutoKey,
                                drawOperation: DrawOperation) =
		component(::WCDrawComponent, WCDrawComponent.Props(key, drawOperation))
