package pl.karol202.uranium.core

class Builder
{
	var elements = emptyList<Element<*>>()
		private set

	fun add(element: Element<*>)
	{
		this.elements = this.elements + element
	}
}
