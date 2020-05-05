package pl.karol202.uranium.webcanvas.component.event

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.base.WCAbstractNativeLeafComponent
import pl.karol202.uranium.webcanvas.native.WCEventNativeLeaf
import pl.karol202.uranium.webcanvas.values.InputEvent

class WCEventHandler(props: Props) : WCAbstractNativeLeafComponent<WCEventHandler.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 val listener: (InputEvent) -> Unit) : UProps

	override val native = WCEventNativeLeaf { props.listener(it) }
}

fun WCRenderScope.eventHandler(key: Any = AutoKey,
                               listener: (InputEvent) -> Unit) =
		component(::WCEventHandler, WCEventHandler.Props(key, listener))
