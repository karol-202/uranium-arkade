package pl.karol202.uranium.core.element

import pl.karol202.uranium.core.common.Props
import pl.karol202.uranium.core.component.UComponent

class UElement<P : Props>(val componentConstructor: (P) -> UComponent<P, *>,
                          override val props: P) : Element<P>
{
	override fun createComponent() = componentConstructor(props)
}
