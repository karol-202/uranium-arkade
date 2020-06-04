package pl.karol202.uranium.webcanvas.component.physics.collider

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.WCRenderBuilder
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.base.WCAbstractComponent
import pl.karol202.uranium.webcanvas.component.base.WCAbstractNativeLeafComponent
import pl.karol202.uranium.webcanvas.component.physics.physicsTransformer
import pl.karol202.uranium.webcanvas.native.WCColliderNativeLeaf
import pl.karol202.uranium.webcanvas.physics.PhysicsContext
import pl.karol202.uranium.webcanvas.physics.collider.Collider
import pl.karol202.uranium.webcanvas.physics.force.Force

class WCCollider(props: Props) : WCAbstractNativeLeafComponent<WCCollider.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 val collider: Collider) : UProps

	override val native = WCColliderNativeLeaf(::provide)

	private fun provide() = props.collider
}

fun WCRenderScope.collider(key: Any = AutoKey,
                           collider: Collider) =
		component(::WCCollider, WCCollider.Props(key, collider))
