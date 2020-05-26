package pl.karol202.uranium.webcanvas.component.event

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.base.WCAbstractNativeLeafComponent
import pl.karol202.uranium.webcanvas.native.WCEventNativeLeaf
import pl.karol202.uranium.webcanvas.input.InputEvent

class WCEventHandler(props: Props) : WCAbstractNativeLeafComponent<WCEventHandler.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 val mouseListener: (InputEvent.Mouse) -> Unit,
	                 val keyListener: (InputEvent.Key) -> Unit,
	                 val allListener: (InputEvent) -> Unit) : UProps

	override val native = WCEventNativeLeaf(::notify)

	private fun notify(event: InputEvent)
	{
		if(event is InputEvent.Mouse) props.mouseListener(event)
		else if(event is InputEvent.Key) props.keyListener(event)
		props.allListener(event)
	}
}

fun WCRenderScope.eventHandler(key: Any = AutoKey,
                               mouseListener: (InputEvent.Mouse) -> Unit = { },
                               keyListener: (InputEvent.Key) -> Unit = { },
                               allListener: (InputEvent) -> Unit = { }) =
		component(::WCEventHandler, WCEventHandler.Props(key, mouseListener, keyListener, allListener))
