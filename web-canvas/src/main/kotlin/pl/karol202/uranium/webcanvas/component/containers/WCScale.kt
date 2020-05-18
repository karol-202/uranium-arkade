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

class WCScale(props: Props) : WCAbstractComponent<WCScale.Props>(props)
{
	data class Props(override val key: Any,
	                 val vector: Vector,
	                 val content: List<WCElement<*>>) : UProps

	override fun URenderBuilder<WC>.render()
	{
		+ drawContainer(beforeDrawOperation = { before() },
		                afterDrawOperation = { after() }) {
			+ eventTransformer(transform = { it.transform() }) {
				+ physicsTransformer(transform = { transform() }) {
					+ colliderTransformer(transform = { transform() }) {
						+ props.content
					}
				}
			}
		}
	}

	private fun DrawContext.before()
	{
		save()
		scale(props.vector.x, props.vector.y)
	}

	private fun DrawContext.after() = restore()

	private fun InputEvent.transform() = when(this)
	{
		is InputEvent.Mouse -> withLocation(location / props.vector ?: Vector.ZERO)
		is InputEvent.Key -> this
	}

	private fun PhysicsContext.transform(): PhysicsContext
	{
		val scale = 1.0 / props.vector ?: return noForces().noColliders()
		return scale(scale)
	}

	private fun Collider.transform() = scale(props.vector)
}

fun WCRenderScope.scale(key: Any = AutoKey,
                        vector: Vector,
                        content: WCRenderBuilder.() -> Unit) =
		component(::WCScale, WCScale.Props(key, vector, content.render()))
