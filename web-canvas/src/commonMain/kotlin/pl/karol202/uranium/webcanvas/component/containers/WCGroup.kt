package pl.karol202.uranium.webcanvas.component.containers

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.webcanvas.*
import pl.karol202.uranium.webcanvas.component.base.WCAbstractComponent

class WCGroup(props: Props) : WCAbstractComponent<WCGroup.Props>(props)
{
	data class Props(override val key: Any,
	                 val content: List<WCElement<*>>) : UProps

	override fun URenderBuilder<WC>.render() { + props.content }
}

fun WCRenderScope.group(key: Any = AutoKey,
                        content: WCRenderBuilder.() -> Unit) =
		component(::WCGroup, WCGroup.Props(key, content.render()))
