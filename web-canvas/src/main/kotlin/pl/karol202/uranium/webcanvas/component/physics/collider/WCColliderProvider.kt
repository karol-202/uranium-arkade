package pl.karol202.uranium.webcanvas.component.physics.collider

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.base.WCAbstractNativeLeafComponent
import pl.karol202.uranium.webcanvas.native.WCColliderNativeLeaf
import pl.karol202.uranium.webcanvas.native.WCEventNativeLeaf
import pl.karol202.uranium.webcanvas.native.WCPhysicsNativeLeaf
import pl.karol202.uranium.webcanvas.physics.PhysicsContext
import pl.karol202.uranium.webcanvas.physics.collider.Collider
import pl.karol202.uranium.webcanvas.values.InputEvent

class WCColliderProvider(props: Props) : WCAbstractNativeLeafComponent<WCColliderProvider.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 val colliderProvider: () -> Collider) : UProps

	override val native = WCColliderNativeLeaf { props.colliderProvider() }
}

fun WCRenderScope.colliderProvider(key: Any = AutoKey,
                                   colliderProvider: () -> Collider) =
		component(::WCColliderProvider, WCColliderProvider.Props(key, colliderProvider))
