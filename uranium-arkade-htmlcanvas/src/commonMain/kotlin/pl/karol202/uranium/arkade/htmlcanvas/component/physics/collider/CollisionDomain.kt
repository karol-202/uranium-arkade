package pl.karol202.uranium.arkade.htmlcanvas.component.physics.collider

import pl.karol202.uranium.arkade.htmlcanvas.Arkade
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeElement
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderBuilder
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.component.base.AbstractNativeContainerComponent
import pl.karol202.uranium.arkade.htmlcanvas.native.ArkadePhysicsNativeContainer
import pl.karol202.uranium.arkade.htmlcanvas.physics.PhysicsContext
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.native.UNativeContainer
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.render

class CollisionDomain(props: Props) : AbstractNativeContainerComponent<CollisionDomain.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 val content: List<ArkadeElement<*>>) : UProps

	private val _native = ArkadePhysicsNativeContainer(::transform)
	override val native: UNativeContainer<Arkade> get() = _native

	override fun URenderBuilder<Arkade>.render() { + props.content }

	private fun transform(context: PhysicsContext): PhysicsContext = context.withColliders(_native.collectColliders().toList())
}

fun ArkadeRenderScope.collisionDomain(key: Any = AutoKey,
                                      content: ArkadeRenderBuilder.() -> Unit) =
		component(::CollisionDomain, CollisionDomain.Props(key, content.render()))
