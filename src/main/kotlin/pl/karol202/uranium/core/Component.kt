package pl.karol202.uranium.core

abstract class Component<P : Props, S : State>(props: P,
                                               state: S) : Detachable
{
	var props = props
		internal set
	var state = state
		set(value)
		{
			field = value
			invalidate()
		}

	private var invalidateCallback: ((Component<P, S>) -> Unit)? = null

	internal fun attach(invalidateCallback: (Component<P, S>) -> Unit)
	{
		this.invalidateCallback = invalidateCallback
		onAttach()
	}

	protected open fun onAttach() { }

	override fun detach()
	{
		invalidateCallback = null
		onDetach()
	}

	protected open fun onDetach() { }

	internal fun render() = Builder().also { it.render() }.elements

	abstract fun Builder.render()

	private fun invalidate() = invalidateCallback?.invoke(this)
}

fun <P : Props> Builder.component(constructor: (P) -> Component<P, *>, props: P) = add(ComponentElement(constructor, props))
