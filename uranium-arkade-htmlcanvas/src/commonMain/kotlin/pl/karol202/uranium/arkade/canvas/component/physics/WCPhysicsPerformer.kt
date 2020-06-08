package pl.karol202.uranium.arkade.canvas.component.physics

import pl.karol202.uranium.arkade.canvas.WCRenderScope
import pl.karol202.uranium.arkade.canvas.component.base.WCAbstractNativeLeafComponent
import pl.karol202.uranium.arkade.canvas.native.WCPhysicsNativeLeaf
import pl.karol202.uranium.arkade.canvas.physics.PhysicsContext
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component

class WCPhysicsPerformer(props: Props) : WCAbstractNativeLeafComponent<WCPhysicsPerformer.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 val listener: (PhysicsContext) -> Unit) : UProps

	override val native = WCPhysicsNativeLeaf(::perform)

	private fun perform(context: PhysicsContext) = props.listener(context)
}

fun WCRenderScope.physicsPerformer(key: Any = AutoKey,
                                   listener: (PhysicsContext) -> Unit) =
		component(::WCPhysicsPerformer, WCPhysicsPerformer.Props(key, listener))
