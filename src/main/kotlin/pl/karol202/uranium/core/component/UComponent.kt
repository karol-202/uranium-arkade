package pl.karol202.uranium.core.component

import pl.karol202.uranium.core.common.*
import pl.karol202.uranium.core.context.InvalidateableContext
import pl.karol202.uranium.core.element.UElement

interface UComponent<C : InvalidateableContext<*>, P : UProps> : Renderable,
                                                                 Attachable<C>,
                                                                 Detachable,
                                                                 HasProps<P>
{
	override var props: P
}

fun <P : UProps> Builder.component(constructor: (P) -> UComponent<*, P>, props: P) = add(UElement(constructor, props))
