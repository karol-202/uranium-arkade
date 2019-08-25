package pl.karol202.uranium.core.component

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.UElement

abstract class AbstractComponent<P : UProps>(override var props: P) : UComponent<P>
{
	protected companion object
	{
		val EMPTY = emptyList<UElement<*>>()
	}

	protected var invalidateListener: (() -> Unit)? = null

	override fun attach(invalidateListener: () -> Unit)
	{
		this.invalidateListener = invalidateListener
		onAttach()
	}

	override fun detach()
	{
		invalidateListener = null
		onDetach()
	}

	protected open fun onAttach() { }

	protected open fun onDetach() { }
}
