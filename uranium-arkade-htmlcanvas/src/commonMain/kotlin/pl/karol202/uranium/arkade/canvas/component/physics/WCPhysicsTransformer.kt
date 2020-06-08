package pl.karol202.uranium.arkade.canvas.component.physics

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

class WCPhysicsTransformer(props: Props) : WCAbstractNativeContainerComponent<WCPhysicsTransformer.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 val transform: (PhysicsContext) -> PhysicsContext,
	                 val content: List<WCElement<*>>) : UProps

	override val native = WCPhysicsNativeContainer(::transform)

	override fun URenderBuilder<WC>.render() { + props.content }

	private fun transform(context: PhysicsContext) = props.transform(context)
}

fun WCRenderScope.physicsTransformer(key: Any = AutoKey,
                                     transform: (PhysicsContext) -> PhysicsContext,
                                     content: WCRenderBuilder.() -> Unit) =
		component(::WCPhysicsTransformer, WCPhysicsTransformer.Props(key, transform, content.render()))
