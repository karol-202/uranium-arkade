package pl.karol202.uranium.core.component

import pl.karol202.uranium.core.element.UElement

class UBuilder<N>
{
	var elements = emptyList<UElement<N, *>>()
		private set

	fun add(element: UElement<N, *>)
	{
		this.elements = this.elements + element
	}
}
