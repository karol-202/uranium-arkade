package pl.karol202.uranium.core.component

import pl.karol202.uranium.core.common.*
import pl.karol202.uranium.core.element.UElement

interface UComponent<P : UProps> : Renderable, Attachable, Detachable, HasProps<P>
{
	override var props: P
}

fun <P : UProps> Builder.component(constructor: (P) -> UComponent<P>, props: P) = add(UElement(constructor, props))
