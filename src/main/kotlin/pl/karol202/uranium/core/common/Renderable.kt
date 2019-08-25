package pl.karol202.uranium.core.common

import pl.karol202.uranium.core.element.Element

interface Renderable
{
	fun render(): List<Element<*>>
}
