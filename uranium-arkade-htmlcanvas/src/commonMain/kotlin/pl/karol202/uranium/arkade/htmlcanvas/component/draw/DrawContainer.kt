package pl.karol202.uranium.arkade.htmlcanvas.component.draw

import pl.karol202.uranium.arkade.htmlcanvas.Arkade
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeElement
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderBuilder
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.component.base.AbstractNativeContainerComponent
import pl.karol202.uranium.arkade.htmlcanvas.draw.DrawContext
import pl.karol202.uranium.arkade.htmlcanvas.draw.DrawOperation
import pl.karol202.uranium.arkade.htmlcanvas.native.ArkadeDrawNativeContainer
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.native.UNativeContainer
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.render

class DrawContainer(props: Props) : AbstractNativeContainerComponent<DrawContainer.Props>(props)
{
	data class Props(override val key: Any,
	                 val beforeDrawOperation: DrawOperation,
	                 val afterDrawOperation: DrawOperation,
	                 val content: List<ArkadeElement<*>>) : UProps

	override val native: UNativeContainer<Arkade> = ArkadeDrawNativeContainer(beforeDrawOperation = { beforeDraw() },
	                                                                          afterDrawOperation = { afterDraw() })

	override fun URenderBuilder<Arkade>.render() { + props.content }

	private fun DrawContext.beforeDraw() = props.beforeDrawOperation(this)

	private fun DrawContext.afterDraw() = props.afterDrawOperation(this)
}

fun ArkadeRenderScope.drawContainer(key: Any = AutoKey,
                                    beforeDrawOperation: DrawOperation,
                                    afterDrawOperation: DrawOperation,
                                    content: ArkadeRenderBuilder.() -> Unit) =
		component(::DrawContainer, DrawContainer.Props(key, beforeDrawOperation, afterDrawOperation, content.render()))
