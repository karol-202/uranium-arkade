package pl.karol202.uranium.arkade.canvas.component.event

import pl.karol202.uranium.arkade.canvas.WC
import pl.karol202.uranium.arkade.canvas.WCElement
import pl.karol202.uranium.arkade.canvas.WCRenderBuilder
import pl.karol202.uranium.arkade.canvas.WCRenderScope
import pl.karol202.uranium.arkade.canvas.component.base.WCAbstractNativeContainerComponent
import pl.karol202.uranium.arkade.canvas.native.WCEventNativeContainer
import pl.karol202.uranium.arkade.canvas.values.InputEvent
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.render

class WCEventTransformer(props: Props) : WCAbstractNativeContainerComponent<WCEventTransformer.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 val transform: (InputEvent) -> InputEvent,
	                 val content: List<WCElement<*>>) : UProps

	override val native = WCEventNativeContainer(::transform)

	override fun URenderBuilder<WC>.render() { + props.content }

	private fun transform(event: InputEvent) = props.transform(event)
}

fun WCRenderScope.eventTransformer(key: Any = AutoKey,
                                   transform: (InputEvent) -> InputEvent,
                                   content: WCRenderBuilder.() -> Unit) =
		component(::WCEventTransformer, WCEventTransformer.Props(key, transform, content.render()))
