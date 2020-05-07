package pl.karol202.uranium.webcanvas.component.physics

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.base.WCAbstractNativeLeafComponent
import pl.karol202.uranium.webcanvas.native.WCEventNativeLeaf
import pl.karol202.uranium.webcanvas.native.WCPhysicsNativeLeaf
import pl.karol202.uranium.webcanvas.physics.PhysicsContext
import pl.karol202.uranium.webcanvas.values.InputEvent

class WCPhysicsPerformer(props: Props) : WCAbstractNativeLeafComponent<WCPhysicsPerformer.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 val listener: PhysicsContext.() -> Unit) : UProps

	override val native = WCPhysicsNativeLeaf { props.listener(it) }
}

fun WCRenderScope.physicsPerformer(key: Any = AutoKey,
                                   listener: PhysicsContext.() -> Unit) =
		component(::WCPhysicsPerformer, WCPhysicsPerformer.Props(key, listener))
