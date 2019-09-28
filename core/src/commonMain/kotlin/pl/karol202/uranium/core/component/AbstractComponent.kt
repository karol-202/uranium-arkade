package pl.karol202.uranium.core.component

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.context.InvalidateableContext
import pl.karol202.uranium.core.context.UContext
import pl.karol202.uranium.core.util.RenderBuilder

abstract class AbstractComponent<N, P : UProps>(props: P) : UComponent<N, P>
{
	final override var props = props
		private set

	private var parentContext: InvalidateableContext<N>? = null

	override val context: UContext<N>? get() = parentContext

	override fun attach(parentContext: InvalidateableContext<N>)
	{
		this.parentContext = parentContext
		onAttach(parentContext)
	}

	override fun detach()
	{
		onDetach(parentContext ?: throw IllegalStateException("Already detached"))
		parentContext = null
	}

	protected open fun onAttach(parentContext: InvalidateableContext<N>) { }

	protected open fun onDetach(parentContext: InvalidateableContext<N>) { }

	protected fun invalidate() = parentContext?.invalidate()

	final override fun render() = RenderBuilder<N>().also { it.render() }.elements

	protected abstract fun RenderBuilder<N>.render()

	override fun modifyPropsInternal(props: P)
	{
		this.props = props
	}
}
