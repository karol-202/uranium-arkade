package pl.karol202.uranium.core

abstract class Renderer
{
	private lateinit var rootNode: TreeNode
	private val registeredComponentNodes = mutableMapOf<Component<*>, TreeNode.ComponentNode>()

	fun render(element: Element<*>)
	{
		if(::rootNode.isInitialized) throw IllegalStateException("Already rendered")
		rootNode = element.toTreeNode()
	}

	private fun Element<*>.toTreeNode() = when(this)
	{
		is ComponentElement<*> -> toComponentTreeNode()
		else -> toPlatformTreeNode()
	}

	protected abstract fun Element<*>.toPlatformTreeNode(): TreeNode.PlatformNode<*>

	private fun ComponentElement<*>.toComponentTreeNode(): TreeNode.ComponentNode
	{
		val component = createAttachedComponent()
		return createRegisteredNode(component).also {
			it.attachChildren(component.renderToNodes())
		}
	}

	private fun ComponentElement<*>.createAttachedComponent() =
			this.createComponent().also { it.attach(this@Renderer::invalidate) }

	private fun createRegisteredNode(component: Component<*>) =
			TreeNode.ComponentNode(component).also { component.register(it) }

	private fun Component<*>.renderToNodes() = render().map { it.toTreeNode() }

	private fun invalidate(component: Component<*>)
	{
		val node = component.findNode()
		node.clear()
		node.attachChildren(component.renderToNodes())
	}

	private fun Component<*>.findNode() = registeredComponentNodes[this] ?: throw IllegalStateException("Unregistered component")

	private fun TreeNode.ComponentNode.clear()
	{
		childrenComponents.forEach { it.unregister() }
		detachChildren()
	}

	private fun Component<*>.register(node: TreeNode.ComponentNode)
	{
		registeredComponentNodes[this] = node
	}

	private fun Component<*>.unregister()
	{
		registeredComponentNodes.remove(this)
	}
}
