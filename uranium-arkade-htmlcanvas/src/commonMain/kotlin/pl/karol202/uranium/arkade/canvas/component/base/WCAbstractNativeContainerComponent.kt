package pl.karol202.uranium.arkade.canvas.component.base

import pl.karol202.uranium.arkade.canvas.WC
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.native.UNativeContainer

abstract class WCAbstractNativeContainerComponent<P : UProps>(props: P) : WCAbstractComponent<P>(props)
{
	abstract override val native: UNativeContainer<WC>
}
