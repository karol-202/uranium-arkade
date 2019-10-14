package pl.karol202.uranium.core.tree

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.UComponent
import pl.karol202.uranium.core.context.UContext
import pl.karol202.uranium.core.context.invalidateable
import pl.karol202.uranium.core.element.UElement

fun <N, P : UProps> UElement<N, P>.createNode(context: UContext<N>, renderScheduler: RenderScheduler<N>) =
		TreeNode(createComponent(), context, renderScheduler)

class TreeNode<N, P : UProps>(private val component: UComponent<N, P>,
                              private val context: UContext<N>,
                              private val scheduler: RenderScheduler<N>)
{
	private val key get() = component.key
	private var children = emptyList<TreeNode<N, *>>()

	fun scheduleInit() = scheduler.submit(this::init)

	fun scheduleReuse(element: UElement<N, P>) = scheduler.submit { reuse(element) }

	private fun init()
	{
		attach()
		invalidate()
	}

	private fun attach() = component.attach(context.invalidateable(this::scheduleInvalidate))

	private fun scheduleInvalidate() = scheduler.submit(this::invalidate)

	private fun invalidate()
	{
		render()
		update()
	}

	private fun render()
	{
		val newElements = component.render()
		val newKeys = newElements.map { it.key }
		detachOldChildren(newKeys)
		updateChildren(newElements)
	}

	private fun detachOldChildren(newKeys: List<Any>) = children.filter { it.key !in newKeys }.forEach { it.detach() }

	private fun updateChildren(newElements: List<UElement<N, *>>)
	{
		children = newElements.map { reuseOrRenderChild(it) }
	}

	private fun reuseOrRenderChild(element: UElement<N, *>) = reuseChild(element) ?: renderChild(element)

	private fun renderChild(element: UElement<N, *>) = createChild(element).apply { init() }

	private fun createChild(element: UElement<N, *>) = element.createNode(component.requireContext(), scheduler)

	private fun <P : UProps> reuseChild(element: UElement<N, P>) = findChildByKey<P>(element.key)?.apply { reuse(element) }

	private fun <P : UProps> findChildByKey(key: Any) = children.firstOrNull { it.key == key } as? TreeNode<N, P>

	private fun reuse(element: UElement<N, P>)
	{
		if(needsUpdate(element)) keepProps { prevProps ->
			setProps(element)
			render()
			update(prevProps)
		}
		else setProps(element)
	}

	// Returning false doesn't have to necessarily mean that props haven't changed
	private fun needsUpdate(newElement: UElement<N, P>) = component.props != newElement.props

	private fun <R> keepProps(block: (P) -> R) = component.props.let(block)

	private fun setProps(newElement: UElement<N, P>) = component.modifyPropsInternal(newElement.props)

	private fun update(previousProps: P? = null) = component.onUpdate(previousProps)

	private fun detach()
	{
		detachChildren()
		component.detach()
	}

	private fun detachChildren()
	{
		children.forEach { it.detach() }
		children = emptyList()
	}
}
