package pl.karol202.uranium.core.context

interface UContext<N>
{
	fun attachNative(nativeComponent: N)

	fun detachNative(nativeComponent: N)
}
