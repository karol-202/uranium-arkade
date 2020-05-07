package pl.karol202.uranium.webcanvas.component.physics

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
import pl.karol202.uranium.webcanvas.native.WCPhysicsNativeContainer
import pl.karol202.uranium.webcanvas.physics.PhysicsContext

class WCPhysicsTransformer(props: Props) : WCAbstractNativeContainerComponent<WCPhysicsTransformer.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 val transform: PhysicsContext.() -> PhysicsContext,
	                 val content: List<WCElement<*>>) : UProps

	override val native = WCPhysicsNativeContainer { props.transform(it) }

	override fun URenderBuilder<WC>.render() { + props.content }
}

fun WCRenderScope.physicsTransformer(key: Any = AutoKey,
                                   transform: PhysicsContext.() -> PhysicsContext,
                                   content: WCRenderBuilder.() -> Unit) =
		component(::WCPhysicsTransformer, WCPhysicsTransformer.Props(key, transform, content.render()))
