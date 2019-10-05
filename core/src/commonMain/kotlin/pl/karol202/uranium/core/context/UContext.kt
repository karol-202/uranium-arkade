package pl.karol202.uranium.core.context

interface UContext<N>
{
	fun attachNative(native: N)

	fun detachNative(native: N)
}
