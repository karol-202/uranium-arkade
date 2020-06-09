package pl.karol202.uranium.arkade.htmlcanvas.component.physics.collider

import pl.karol202.uranium.arkade.htmlcanvas.Arkade
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.component.base.ArkadeAbstractNativeLeafComponent
import pl.karol202.uranium.arkade.htmlcanvas.native.ColliderNativeLeaf
import pl.karol202.uranium.arkade.htmlcanvas.physics.collider.Collider
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.native.UNative

class ColliderProvider(props: Props) : ArkadeAbstractNativeLeafComponent<ColliderProvider.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 val collider: Collider) : UProps

	override val native: UNative<Arkade> = ColliderNativeLeaf(::provide)

	private fun provide() = props.collider
}

fun ArkadeRenderScope.colliderProvider(key: Any = AutoKey,
                                       collider: Collider) =
		component(::ColliderProvider, ColliderProvider.Props(key, collider))
