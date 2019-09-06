package pl.karol202.uranium.core.component

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.context.InvalidateableContext
import pl.karol202.uranium.core.element.UElement

abstract class AbstractComponent<C : InvalidateableContext<*>, P : UProps>(override var props: P) : UComponent<C, P>
{
	protected companion object
	{
		val EMPTY = emptyList<UElement<*, *>>()
	}

	protected var context: C? = null

	override fun attach(context: C)
	{
		this.context = context
		onAttach()
	}

	override fun detach()
	{
		onDetach()
		context = null
	}

	protected open fun onAttach() { }

	protected open fun onDetach() { }
}
