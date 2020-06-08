package pl.karol202.uranium.arkade.htmlcanvas.component.physics

import pl.karol202.uranium.arkade.htmlcanvas.Arkade
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.component.base.AbstractNativeLeafComponent
import pl.karol202.uranium.arkade.htmlcanvas.native.ArkadePhysicsNativeLeaf
import pl.karol202.uranium.arkade.htmlcanvas.physics.PhysicsContext
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.native.UNative

class PhysicsPerformer(props: Props) : AbstractNativeLeafComponent<PhysicsPerformer.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 val listener: (PhysicsContext) -> Unit) : UProps

	override val native: UNative<Arkade> = ArkadePhysicsNativeLeaf(::perform)

	private fun perform(context: PhysicsContext) = props.listener(context)
}

fun ArkadeRenderScope.physicsPerformer(key: Any = AutoKey,
                                       listener: (PhysicsContext) -> Unit) =
		component(::PhysicsPerformer, PhysicsPerformer.Props(key, listener))
