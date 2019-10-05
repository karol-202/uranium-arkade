package pl.karol202.uranium.core.context

interface ContextProvider<N>
{
	val context: UContext<N>?

	fun requireContext() = context ?: throw NullPointerException("No context")
}
