package pl.karol202.uranium.arkade.canvas.component.draw

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.arkade.canvas.WCRenderScope
import pl.karol202.uranium.arkade.canvas.component.base.WCAbstractNativeLeafComponent
import pl.karol202.uranium.arkade.canvas.draw.DrawContext
import pl.karol202.uranium.arkade.canvas.native.WCDrawNativeLeaf
import pl.karol202.uranium.arkade.canvas.draw.DrawOperation

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
