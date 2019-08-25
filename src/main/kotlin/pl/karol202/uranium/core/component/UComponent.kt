package pl.karol202.uranium.core.component

import pl.karol202.uranium.core.common.Props
import pl.karol202.uranium.core.common.State
import pl.karol202.uranium.core.element.UElement

abstract class UComponent<P : Props, S : State>(props: P,
                                                state: S) : Component
{
	var props = props
		internal set
	var state = state
		set(value)
		{
			field = value
			invalidate()
		}

	override val key get() = props.key

	private var invalidateListener: (() -> Unit)? = null

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

	override fun render() = Builder().also { it.render() }.elements

	protected abstract fun Builder.render()

	private fun invalidate() = invalidateListener?.invoke()
}

fun <P : Props> Builder.component(constructor: (P) -> UComponent<P, *>, props: P) = add(UElement(constructor, props))
