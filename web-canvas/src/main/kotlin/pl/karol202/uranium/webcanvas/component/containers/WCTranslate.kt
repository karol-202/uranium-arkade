package pl.karol202.uranium.webcanvas.component.containers

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.URenderScope
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.webcanvas.*
import pl.karol202.uranium.webcanvas.component.base.WCAbstractComponent
import pl.karol202.uranium.webcanvas.component.draw.drawContainer
import pl.karol202.uranium.webcanvas.component.event.eventTransformer
import pl.karol202.uranium.webcanvas.draw.DrawContext
import pl.karol202.uranium.webcanvas.values.InputEvent
import pl.karol202.uranium.webcanvas.values.Vector

class WCTranslate(props: Props) : WCAbstractComponent<WCTranslate.Props>(props)
{
	data class Props(override val key: Any,
	                 val vector: Vector,
	                 val content: List<WCElement<*>>) : UProps

	override fun URenderBuilder<WC>.render()
	{
		+ drawContainer(beforeDrawOperation = { before() },
		                afterDrawOperation = { after() }) {
			+ eventTransformer(transform = { it.transform() }) {
				+ props.content
			}
		}
	}

	private fun DrawContext.before()
	{
		save()
		translate(props.vector.x, props.vector.y)
	}

	private fun DrawContext.after() = restore()

	private fun InputEvent.transform() = when(this)
	{
		is InputEvent.Mouse -> withLocation { it - props.vector }
	}
}

fun WCRenderScope.translate(key: Any = AutoKey,
                            vector: Vector,
                            content: WCRenderBuilder.() -> Unit) =
		component(::WCTranslate, WCTranslate.Props(key, vector, content.render()))
