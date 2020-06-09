package pl.karol202.uranium.arkade.htmlcanvas.component.base

import pl.karol202.uranium.arkade.htmlcanvas.Arkade
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.native.UNativeContainer

abstract class ArkadeAbstractNativeContainerComponent<P : UProps>(props: P) : ArkadeAbstractComponent<P>(props)
{
	abstract override val native: UNativeContainer<Arkade>
}
