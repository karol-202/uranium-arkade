package pl.karol202.uranium.arkade.htmlcanvas.component.event

import pl.karol202.uranium.arkade.htmlcanvas.Arkade
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeElement
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderBuilder
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.component.base.ArkadeAbstractNativeContainerComponent
import pl.karol202.uranium.arkade.htmlcanvas.native.ArkadeEventNativeContainer
import pl.karol202.uranium.arkade.htmlcanvas.values.InputEvent
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.native.UNativeContainer
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.render

class EventTransformer(props: Props) : ArkadeAbstractNativeContainerComponent<EventTransformer.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 val transform: (InputEvent) -> InputEvent,
	                 val content: List<ArkadeElement<*>>) : UProps

	override val native: UNativeContainer<Arkade> = ArkadeEventNativeContainer(::transform)

	override fun URenderBuilder<Arkade>.render() { + props.content }

	private fun transform(event: InputEvent) = props.transform(event)
}

fun ArkadeRenderScope.eventTransformer(key: Any = AutoKey,
                                       transform: (InputEvent) -> InputEvent,
                                       content: ArkadeRenderBuilder.() -> Unit) =
		component(::EventTransformer, EventTransformer.Props(key, transform, content.render()))
