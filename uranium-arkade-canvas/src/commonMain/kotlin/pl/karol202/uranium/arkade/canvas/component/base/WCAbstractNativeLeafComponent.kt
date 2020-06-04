package pl.karol202.uranium.arkade.canvas.component.base

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.UAbstractComponent
import pl.karol202.uranium.core.native.UNative
import pl.karol202.uranium.arkade.canvas.WC
import pl.karol202.uranium.arkade.canvas.WCElement

abstract class WCAbstractNativeLeafComponent<P : UProps>(props: P) : UAbstractComponent<WC, P>(props)
{
	abstract override val native: UNative<WC>

	final override fun render() = emptyList<WCElement<*>>()

	override fun needsUpdate(newProps: P) = false
}
