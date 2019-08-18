package pl.karol202.uranium.core

sealed class TreeNode(private val content: Detachable) : Detachable
{
	class ComponentNode(private val component: Component<*>) : TreeNode(component)
	{
		override val allComponents get() = childrenComponents + component
	}

	class PlatformNode<E : Detachable>(platformElement: E) : TreeNode(platformElement)
	{
		override val allComponents get() = childrenComponents
	}

	private var children = emptyList<TreeNode>()

	abstract val allComponents: List<Component<*>>
	val childrenComponents: List<Component<*>> get() = children.flatMap { it.allComponents }

	fun attachChildren(children: List<TreeNode>)
	{
		this.children += children
	}

	override fun detach()
	{
		detachChildren()
		content.detach()
	}

	fun detachChildren()
	{
		children.forEach { it.detach() }
		children = emptyList()
	}
}
