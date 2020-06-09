package pl.karol202.uranium.arkade.htmlcanvas.component.containers

import pl.karol202.uranium.arkade.htmlcanvas.Arkade
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeElement
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderBuilder
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.component.base.ArkadeAbstractComponent
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.render

class Group(props: Props) : ArkadeAbstractComponent<Group.Props>(props)
{
	data class Props(override val key: Any,
	                 val content: List<ArkadeElement<*>>) : UProps

	override fun URenderBuilder<Arkade>.render() { + props.content }
}

fun ArkadeRenderScope.group(key: Any = AutoKey,
                            content: ArkadeRenderBuilder.() -> Unit) =
		component(::Group, Group.Props(key, content.render()))
