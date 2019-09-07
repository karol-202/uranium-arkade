package pl.karol202.uranium.core.context

import pl.karol202.uranium.core.common.Invalidateable

class InvalidateableContext<N>(private val context: UContext<N>,
                               private val invalidateListener: () -> Unit) : UContext<N> by context, Invalidateable
{
	override fun invalidate() = invalidateListener()
}

fun <N> UContext<N>.invalidateable(listener: () -> Unit) = InvalidateableContext(this, listener)
