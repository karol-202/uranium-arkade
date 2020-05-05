package pl.karol202.uranium.webcanvas.component.base

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.UNativeComponent
import pl.karol202.uranium.core.native.UNativeContainer
import pl.karol202.uranium.webcanvas.WC

abstract class WCAbstractNativeContainerComponent<P : UProps>(props: P) : WCAbstractComponent<P>(props),
                                                                          UNativeComponent<WC, P>
{
	abstract override val native: UNativeContainer<WC>
}
