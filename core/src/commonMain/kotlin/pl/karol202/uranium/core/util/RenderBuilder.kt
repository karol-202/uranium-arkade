package pl.karol202.uranium.core.util

import pl.karol202.uranium.core.element.UElement

class RenderBuilder<N>
{
	var elements = emptyList<UElement<N, *>>()
		private set

	operator fun ComponentBuilder<N, *>.unaryPlus() = add(build())

	fun add(element: UElement<N, *>) = element.also {
		this.elements = this.elements + it
	}
}
