package pl.karol202.uranium.core.common

import pl.karol202.uranium.core.element.UElement

interface Renderable
{
	fun render(): List<UElement<*, *>>
}
