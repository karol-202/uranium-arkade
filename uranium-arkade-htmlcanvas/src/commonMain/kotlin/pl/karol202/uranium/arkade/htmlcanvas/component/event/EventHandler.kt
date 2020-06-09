package pl.karol202.uranium.arkade.htmlcanvas.component.event

import pl.karol202.uranium.arkade.htmlcanvas.Arkade
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.component.base.ArkadeAbstractNativeLeafComponent
import pl.karol202.uranium.arkade.htmlcanvas.native.ArkadeEventNativeLeaf
import pl.karol202.uranium.arkade.htmlcanvas.values.InputEvent
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.native.UNative

class EventHandler(props: Props) : ArkadeAbstractNativeLeafComponent<EventHandler.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 val mouseListener: (InputEvent.Mouse) -> Unit,
	                 val keyListener: (InputEvent.Key) -> Unit,
	                 val allListener: (InputEvent) -> Unit) : UProps

	override val native: UNative<Arkade> = ArkadeEventNativeLeaf(::notify)

	private fun notify(event: InputEvent)
	{
		if(event is InputEvent.Mouse) props.mouseListener(event)
		else if(event is InputEvent.Key) props.keyListener(event)
		props.allListener(event)
	}
}

fun ArkadeRenderScope.eventHandler(key: Any = AutoKey,
                                   mouseListener: (InputEvent.Mouse) -> Unit = { },
                                   keyListener: (InputEvent.Key) -> Unit = { },
                                   allListener: (InputEvent) -> Unit = { }) =
		component(::EventHandler, EventHandler.Props(key, mouseListener, keyListener, allListener))
