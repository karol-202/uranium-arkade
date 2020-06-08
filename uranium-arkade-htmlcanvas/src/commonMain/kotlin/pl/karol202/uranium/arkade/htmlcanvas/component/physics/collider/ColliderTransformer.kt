package pl.karol202.uranium.arkade.htmlcanvas.component.physics.collider

import pl.karol202.uranium.arkade.htmlcanvas.Arkade
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeElement
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderBuilder
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.component.base.AbstractNativeContainerComponent
import pl.karol202.uranium.arkade.htmlcanvas.native.ColliderNativeContainer
import pl.karol202.uranium.arkade.htmlcanvas.physics.collider.Collider
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.native.UNativeContainer
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.render

class ColliderTransformer(props: Props) : AbstractNativeContainerComponent<ColliderTransformer.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 val transform: Collider.() -> Collider,
	                 val content: List<ArkadeElement<*>>) : UProps

	override val native: UNativeContainer<Arkade> = ColliderNativeContainer(::transform)

	override fun URenderBuilder<Arkade>.render() { + props.content }

	private fun transform(collider: Collider) = props.transform(collider)
}

fun ArkadeRenderScope.colliderTransformer(key: Any = AutoKey,
                                          transform: Collider.() -> Collider,
                                          content: ArkadeRenderBuilder.() -> Unit) =
		component(::ColliderTransformer, ColliderTransformer.Props(key, transform, content.render()))
