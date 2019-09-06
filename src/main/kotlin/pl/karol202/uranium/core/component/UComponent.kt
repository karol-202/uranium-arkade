package pl.karol202.uranium.core.component

import pl.karol202.uranium.core.common.*
import pl.karol202.uranium.core.context.InvalidateableContext
import pl.karol202.uranium.core.element.UElement

interface UComponent<N, P : UProps> : Renderable<N>,
                                      Attachable<InvalidateableContext<N>>,
                                      Detachable,
                                      ContextProvider<N>,
                                      PropsProvider<P>
{
	fun modifyPropsInternal(props: P)
}

fun <N, P : UProps> UBuilder<N>.component(constructor: (P) -> UComponent<N, P>, props: P) =
		add(UElement(constructor, props))
