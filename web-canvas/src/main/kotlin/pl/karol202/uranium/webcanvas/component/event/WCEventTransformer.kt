package pl.karol202.uranium.webcanvas.component.event

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.WCElement
import pl.karol202.uranium.webcanvas.WCRenderBuilder
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.base.WCAbstractNativeContainerComponent
import pl.karol202.uranium.webcanvas.native.WCEventNativeContainer
import pl.karol202.uranium.webcanvas.values.InputEvent

class WCEventTransformer(props: Props) : WCAbstractNativeContainerComponent<WCEventTransformer.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 val transform: (InputEvent) -> InputEvent,
	                 val content: List<WCElement<*>>) : UProps

	override val native = WCEventNativeContainer { props.transform(it) }

	override fun URenderBuilder<WC>.render() { + props.content }
}

fun WCRenderScope.eventTransformer(key: Any = AutoKey,
                                   transform: (InputEvent) -> InputEvent,
                                   content: WCRenderBuilder.() -> Unit) =
		component(::WCEventTransformer, WCEventTransformer.Props(key, transform, content.render()))
