package pl.karol202.uranium.core.context

class InvalidateableContext<N>(private val context: UContext<N>,
                               val invalidate: () -> Unit) : UContext<N> by context

fun <N> UContext<N>.invalidateable(listener: () -> Unit) = InvalidateableContext(this, listener)
