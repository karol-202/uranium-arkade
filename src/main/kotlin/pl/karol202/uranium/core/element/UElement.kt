package pl.karol202.uranium.core.element

import pl.karol202.uranium.core.common.HasProps
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.UComponent

class UElement<P : UProps>(val componentConstructor: (P) -> UComponent<P>,
                           override val props: P) : HasProps<P>
{
	fun createComponent() = componentConstructor(props)
}
