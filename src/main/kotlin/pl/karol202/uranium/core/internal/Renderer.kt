package pl.karol202.uranium.core.internal

import pl.karol202.uranium.core.component.UComponent
import pl.karol202.uranium.core.context.UContext
import pl.karol202.uranium.core.context.invalidateable
import pl.karol202.uranium.core.element.UElement

class Renderer<N>
{
	private lateinit var rootNode: TreeNode<N, *>

	fun renderRoot(element: UElement<N, *>, context: UContext<N>)
	{
		check(!::rootNode.isInitialized) { "Already rendered" }
		rootNode = renderElement(element, context)
	}

	fun renderElement(element: UElement<N, *>, context: UContext<N>) = element.toNode(context).rendered()

	private fun UElement<N, *>.toNode(context: UContext<N>) = createComponent().attached(context).toNode()

	private fun UComponent<N, *>.attached(context: UContext<N>) = also { attach(context.invalidateable { rendered() }) }

	private fun UComponent<N, *>.toNode() = TreeNode(this)

	private fun UComponent<N, *>.rendered() = findComponentNode(this).rendered()

	private fun TreeNode<N, *>.rendered() = also { render(this@Renderer) }

	private fun findComponentNode(component: UComponent<N, *>) = rootNode.findNode(component) ?: throw IllegalArgumentException()
}
