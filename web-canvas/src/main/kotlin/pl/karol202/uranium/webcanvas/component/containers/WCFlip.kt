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
import pl.karol202.uranium.webcanvas.component.physics.collider.colliderTransformer
import pl.karol202.uranium.webcanvas.component.physics.physicsTransformer
import pl.karol202.uranium.webcanvas.draw.DrawContext
import pl.karol202.uranium.webcanvas.physics.PhysicsContext
import pl.karol202.uranium.webcanvas.physics.collider.Collider
import pl.karol202.uranium.webcanvas.values.InputEvent
import pl.karol202.uranium.webcanvas.values.Vector
import pl.karol202.uranium.webcanvas.values.div

class WCFlip(props: Props) : WCAbstractComponent<WCFlip.Props>(props)
{
	data class Props(override val key: Any,
	                 val horizontal: Boolean,
	                 val vertical: Boolean,
	                 val content: List<WCElement<*>>) : UProps

	private val scale get() = Vector(if(props.horizontal) -1.0 else 1.0,
	                                 if(props.vertical) -1.0 else 1.0)

	override fun URenderBuilder<WC>.render()
	{
		+ scale(vector = scale) {
			+ props.content
		}
	}
}

fun WCRenderScope.flip(key: Any = AutoKey,
                       horizontal: Boolean = false,
                       vertical: Boolean = false,
                       content: WCRenderBuilder.() -> Unit) =
		component(::WCFlip, WCFlip.Props(key, horizontal, vertical, content.render()))
