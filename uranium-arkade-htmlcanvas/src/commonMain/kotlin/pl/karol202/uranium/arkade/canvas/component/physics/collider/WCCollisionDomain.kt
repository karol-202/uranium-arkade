package pl.karol202.uranium.arkade.canvas.component.physics.collider

import pl.karol202.uranium.arkade.canvas.WC
import pl.karol202.uranium.arkade.canvas.WCElement
import pl.karol202.uranium.arkade.canvas.WCRenderBuilder
import pl.karol202.uranium.arkade.canvas.WCRenderScope
import pl.karol202.uranium.arkade.canvas.component.base.WCAbstractNativeContainerComponent
import pl.karol202.uranium.arkade.canvas.native.WCPhysicsNativeContainer
import pl.karol202.uranium.arkade.canvas.physics.PhysicsContext
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.render

class WCCollisionDomain(props: Props) : WCAbstractNativeContainerComponent<WCCollisionDomain.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 val content: List<WCElement<*>>) : UProps

	override val native = WCPhysicsNativeContainer(::transform)

	override fun URenderBuilder<WC>.render() { + props.content }

	private fun transform(context: PhysicsContext): PhysicsContext = context.withColliders(native.collectColliders().toList())
}

fun WCRenderScope.collisionDomain(key: Any = AutoKey,
                                  content: WCRenderBuilder.() -> Unit) =
		component(::WCCollisionDomain, WCCollisionDomain.Props(key, content.render()))
