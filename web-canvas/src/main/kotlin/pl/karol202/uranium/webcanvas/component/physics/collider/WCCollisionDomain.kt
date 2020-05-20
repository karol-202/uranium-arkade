package pl.karol202.uranium.webcanvas.component.physics.collider

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
import pl.karol202.uranium.webcanvas.native.WCColliderNativeContainer
import pl.karol202.uranium.webcanvas.native.WCPhysicsNativeContainer
import pl.karol202.uranium.webcanvas.physics.PhysicsContext
import pl.karol202.uranium.webcanvas.physics.collider.Collider

class WCCollisionDomain(props: Props) : WCAbstractNativeContainerComponent<WCCollisionDomain.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 val content: List<WCElement<*>>) : UProps

	override val native = WCPhysicsNativeContainer { it.transform() }

	override fun URenderBuilder<WC>.render() { + props.content }

	private fun PhysicsContext.transform(): PhysicsContext = withColliders(native.collectColliders().toList())
}

fun WCRenderScope.collisionDomain(key: Any = AutoKey,
                                  content: WCRenderBuilder.() -> Unit) =
		component(::WCCollisionDomain, WCCollisionDomain.Props(key, content.render()))
