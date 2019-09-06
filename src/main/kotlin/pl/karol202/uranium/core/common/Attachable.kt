package pl.karol202.uranium.core.common

import pl.karol202.uranium.core.context.Context

interface Attachable<C : Context<*>>
{
	fun attach(context: C)
}
