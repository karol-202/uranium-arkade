package pl.karol202.uranium.arkade.htmlcanvas.component.physics

import pl.karol202.uranium.arkade.htmlcanvas.Arkade
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeElement
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderBuilder
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.component.base.ArkadeAbstractNativeContainerComponent
import pl.karol202.uranium.arkade.htmlcanvas.native.ArkadePhysicsNativeContainer
import pl.karol202.uranium.arkade.htmlcanvas.physics.PhysicsContext
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.native.UNativeContainer
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.render

class PhysicsTransformer(props: Props) : ArkadeAbstractNativeContainerComponent<PhysicsTransformer.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 val transform: (PhysicsContext) -> PhysicsContext,
	                 val content: List<ArkadeElement<*>>) : UProps

	override val native: UNativeContainer<Arkade> = ArkadePhysicsNativeContainer(::transform)

	override fun URenderBuilder<Arkade>.render() { + props.content }

	private fun transform(context: PhysicsContext) = props.transform(context)
}

fun ArkadeRenderScope.physicsTransformer(key: Any = AutoKey,
                                         transform: (PhysicsContext) -> PhysicsContext,
                                         content: ArkadeRenderBuilder.() -> Unit) =
		component(::PhysicsTransformer, PhysicsTransformer.Props(key, transform, content.render()))
