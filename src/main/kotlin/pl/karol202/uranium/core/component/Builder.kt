package pl.karol202.uranium.core.component

import pl.karol202.uranium.core.element.UElement

class Builder
{
	var elements = emptyList<UElement<*>>()
		private set

	fun add(element: UElement<*>)
	{
		this.elements = this.elements + element
	}
}
