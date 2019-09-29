package pl.karol202.uranium.core.element

import pl.karol202.uranium.core.common.PropsProvider
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.UComponent

class UElement<N, P : UProps>(private val constructor: (P) -> UComponent<N, P>,
                              override val props: P) : PropsProvider<P>
{
	fun withProps(propsBuilder: P.() -> P) = UElement(constructor, props.propsBuilder())

	internal fun createComponent() = constructor(props)
}
