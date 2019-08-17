package pl.karol202.uranium.core

abstract class Component<P : Props>(props: P)
{
	private var invalidateCallback: ((Component<P>) -> Unit)? = null

	internal fun mount(invalidateCallback: (Component<P>) -> Unit)
	{
		this.invalidateCallback = invalidateCallback
		onMount()
	}

	protected open fun onMount() { }

	abstract fun Builder.render(): Node<*>

	internal fun unmount()
	{
		invalidateCallback = null
		onUnmount()
	}

	protected open fun onUnmount() { }
}

fun <P : Props> Builder.component(constructor: (P) -> Component<P>, props: P) = ComponentNode(constructor, props)
