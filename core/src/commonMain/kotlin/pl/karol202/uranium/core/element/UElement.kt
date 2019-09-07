package pl.karol202.uranium.core.element

import pl.karol202.uranium.core.common.PropsProvider
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.UComponent

class UElement<N, P : UProps>(private val componentConstructor: (P) -> UComponent<N, P>,
                              override val props: P) : PropsProvider<P>
{
	fun createComponent() = componentConstructor(props)
}
