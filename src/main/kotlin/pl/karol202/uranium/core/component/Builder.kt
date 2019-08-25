package pl.karol202.uranium.core.component

import pl.karol202.uranium.core.element.Element

class Builder
{
	var elements = emptyList<Element<*>>()
		private set

	fun add(element: Element<*>)
	{
		this.elements = this.elements + element
	}
}
