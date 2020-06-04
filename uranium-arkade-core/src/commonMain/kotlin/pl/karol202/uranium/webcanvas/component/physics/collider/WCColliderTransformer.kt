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

class WCColliderTransformer(props: Props) : WCAbstractNativeContainerComponent<WCColliderTransformer.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 val transform: Collider.() -> Collider,
	                 val content: List<WCElement<*>>) : UProps

	override val native = WCColliderNativeContainer(::transform)

	override fun URenderBuilder<WC>.render() { + props.content }

	private fun transform(collider: Collider) = props.transform(collider)
}

fun WCRenderScope.colliderTransformer(key: Any = AutoKey,
                                     transform: Collider.() -> Collider,
                                     content: WCRenderBuilder.() -> Unit) =
		component(::WCColliderTransformer, WCColliderTransformer.Props(key, transform, content.render()))
