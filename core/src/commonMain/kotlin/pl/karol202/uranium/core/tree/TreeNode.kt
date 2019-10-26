package pl.karol202.uranium.core.tree

import pl.karol202.uranium.core.common.KeyProvider
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.UComponent
import pl.karol202.uranium.core.element.UElement
import pl.karol202.uranium.core.schedule.RenderScheduler
import pl.karol202.uranium.core.tree.TreeNodeOperation.*
import pl.karol202.uranium.core.util.addAtIndex
import kotlin.reflect.KClass

fun <N, P : UProps> UElement<N, P>.createNode(renderScheduler: RenderScheduler<N>) =
		TreeNode(createComponent(), renderScheduler, propsClass)

class TreeNode<N, P : UProps> internal constructor(private val component: UComponent<N, P>,
                                                   private val scheduler: RenderScheduler<N>,
                                                   private val propsClass: KClass<P>) : KeyProvider
{
	override val key get() = component.key
	private var children = emptyList<TreeNode<N, *>>()

	fun scheduleInit() = scheduler.submit { init() }

	suspend fun scheduleInitAndWait() = scheduler.submitAndWait { init() }

	fun scheduleReuse(element: UElement<N, P>) = scheduler.submit { reuse(element) }

	suspend fun scheduleReuseAndWait(element: UElement<N, P>) = scheduler.submitAndWait { reuse(element) }

	private fun init()
	{
		createComponent()
		render()
		update()
	}

	private fun createComponent() = component.create(componentContext { scheduleInvalidate() })

	private fun scheduleInvalidate() = scheduler.submit(this::invalidate)

	private fun invalidate()
	{
		render()
		update(component.props)
	}

	private fun render()
	{
		children = dispatchDiff(children, component.render()).fold(children) { children, op -> op.applyTo(children) }
	}

	private fun TreeNodeOperation<N>.applyTo(children: List<TreeNode<N, *>>) = when(this)
	{
		is CreateAndAttachNode<N> -> children.addAtIndex(createChild(element), index)
		is UpdateNode<N, *> -> children.also { this.apply() }
		is DestroyAndDetachNode<N> -> children - node.also { it.destroy() }
		is AttachNode<N> -> children.addAtIndex(node, index)
		is DetachNode<N> -> children - node
	}

	private fun createChild(element: UElement<N, *>) = element.createNode(scheduler).also { it.init() }

	private fun <P : UProps> UpdateNode<N, P>.apply() = node.reuse(element)

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

	private fun destroy()
	{
		destroyChildren()
		destroyComponent()
	}

	private fun destroyChildren()
	{
		children.forEach { it.destroy() }
		children = emptyList()
	}

	private fun destroyComponent() = component.destroy()

	internal fun isCompatibleWith(element: UElement<N, *>) = propsClass == element.propsClass
}
