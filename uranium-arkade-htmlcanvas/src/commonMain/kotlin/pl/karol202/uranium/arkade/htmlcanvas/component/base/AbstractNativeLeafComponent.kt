package pl.karol202.uranium.arkade.htmlcanvas.component.base

import pl.karol202.uranium.arkade.htmlcanvas.Arkade
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeElement
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.UAbstractComponent
import pl.karol202.uranium.core.native.UNative

abstract class AbstractNativeLeafComponent<P : UProps>(props: P) : UAbstractComponent<Arkade, P>(props)
{
	abstract override val native: UNative<Arkade>

	final override fun render() = emptyList<ArkadeElement<*>>()

	override fun needsUpdate(newProps: P) = false
}
