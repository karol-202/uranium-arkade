package pl.karol202.uranium.core.component

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.context.InvalidateableContext
import pl.karol202.uranium.core.context.UContext
import pl.karol202.uranium.core.render.RenderBuilder

abstract class AbstractComponent<N, P : UProps>(props: P) : UComponent<N, P>
{
	final override var props = props
		private set

	var parentContext: InvalidateableContext<N>? = null
		private set

	override val context: UContext<N>? get() = parentContext

	final override fun attach(parentContext: InvalidateableContext<N>)
	{
		this.parentContext = parentContext
		onAttach(parentContext)
	}

	final override fun detach()
	{
		onDetach(requireParentContext())
		parentContext = null
	}

	protected open fun onAttach(parentContext: InvalidateableContext<N>) { }

	protected open fun onDetach(parentContext: InvalidateableContext<N>) { }

	protected fun invalidate() = requireParentContext().invalidate()

	final override fun render() = RenderBuilder<N>().also { it.render() }.elements

	protected abstract fun RenderBuilder<N>.render()

	override fun onUpdate(previousProps: P?) { }

	final override fun modifyPropsInternal(props: P)
	{
		this.props = props
	}

	protected fun requireParentContext() = parentContext ?: throw IllegalStateException("Not attached")
}
