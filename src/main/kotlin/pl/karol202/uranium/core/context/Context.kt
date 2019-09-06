package pl.karol202.uranium.core.context

import pl.karol202.uranium.core.common.Invalidateable

interface Context<N> : Invalidateable
{
	fun attachNative(nativeComponent: N)

	fun detachNative(nativeComponent: N)
}
