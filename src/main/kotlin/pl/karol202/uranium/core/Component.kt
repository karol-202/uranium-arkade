package pl.karol202.uranium.core

abstract class Component<P : Props>(props: P) : Detachable
{
	private var invalidateCallback: ((Component<P>) -> Unit)? = null

	internal fun attach(invalidateCallback: (Component<P>) -> Unit)
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
}

fun <P : Props> Builder.component(constructor: (P) -> Component<P>, props: P) = add(ComponentElement(constructor, props))
