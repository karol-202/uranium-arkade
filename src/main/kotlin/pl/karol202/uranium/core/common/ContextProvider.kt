package pl.karol202.uranium.core.common

import pl.karol202.uranium.core.context.UContext

interface ContextProvider<N>
{
	val context: UContext<N>?
}
