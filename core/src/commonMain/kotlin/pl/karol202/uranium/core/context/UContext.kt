package pl.karol202.uranium.core.context

interface UContext<N>
{
	fun attachNative(native: N)

	fun detachNative(native: N)
}

interface ContextProvider<N>
{
	val context: UContext<N>?

	fun requireContext() = context ?: throw NullPointerException("No context")
}
