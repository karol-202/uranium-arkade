package pl.karol202.uranium.arkade.canvas.component.physics.collider

import pl.karol202.uranium.arkade.canvas.WCRenderScope
import pl.karol202.uranium.arkade.canvas.component.base.WCAbstractNativeLeafComponent
import pl.karol202.uranium.arkade.canvas.native.WCColliderNativeLeaf
import pl.karol202.uranium.arkade.canvas.physics.collider.Collider
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component

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
