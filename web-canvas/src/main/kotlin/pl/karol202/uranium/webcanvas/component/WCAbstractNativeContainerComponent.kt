package pl.karol202.uranium.webcanvas.component

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.UAbstractComponent
import pl.karol202.uranium.core.component.UNativeComponent
import pl.karol202.uranium.core.native.UNative
import pl.karol202.uranium.core.native.UNativeContainer
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.WCElement
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.renderScope

abstract class WCAbstractNativeContainerComponent<P : UProps>(props: P) : WCAbstractComponent<P>(props),
                                                                          UNativeComponent<WC, P>
{
	abstract override val native: UNativeContainer<WC>
}
