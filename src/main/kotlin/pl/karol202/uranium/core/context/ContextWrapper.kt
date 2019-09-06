package pl.karol202.uranium.core.context

class ContextWrapper<N>(private val context: Context<N>,
                        private val invalidateListener: () -> Unit) : Context<N> by context
{
	override fun invalidate() = invalidateListener()
}
