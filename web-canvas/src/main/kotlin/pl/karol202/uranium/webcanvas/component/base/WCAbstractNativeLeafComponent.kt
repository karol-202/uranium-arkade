package pl.karol202.uranium.webcanvas.component.base

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.UAbstractComponent
import pl.karol202.uranium.core.component.UNativeComponent
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.WCElement

abstract class WCAbstractNativeLeafComponent<P : UProps>(props: P) : UAbstractComponent<WC, P>(props),
                                                                     UNativeComponent<WC, P>
{
	final override fun render() = emptyList<WCElement<*>>()
}
