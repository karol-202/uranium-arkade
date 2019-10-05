package pl.karol202.uranium.core.component

import pl.karol202.uranium.core.common.Attachable
import pl.karol202.uranium.core.common.Detachable
import pl.karol202.uranium.core.common.PropsProvider
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.context.ContextProvider
import pl.karol202.uranium.core.context.InvalidateableContext
import pl.karol202.uranium.core.element.UElement
import pl.karol202.uranium.core.util.RenderBuilder

interface UComponent<N, P : UProps> : Attachable<InvalidateableContext<N>>,
                                      Detachable,
                                      ContextProvider<N>,
                                      PropsProvider<P>
{
	fun render(): List<UElement<N, *>>

	fun onUpdate(previousProps: P)

	fun modifyPropsInternal(props: P)
}

fun <N, P : UProps> RenderBuilder<N>.component(constructor: (P) -> UComponent<N, P>, props: P) = UElement(constructor, props)
