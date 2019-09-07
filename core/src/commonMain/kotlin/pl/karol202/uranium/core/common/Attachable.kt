package pl.karol202.uranium.core.common

import pl.karol202.uranium.core.context.UContext

interface Attachable<C : UContext<*>>
{
	fun attach(parentContext: C)
}
