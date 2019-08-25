package pl.karol202.uranium.core.internal

import pl.karol202.uranium.core.component.UComponent
import pl.karol202.uranium.core.element.UElement

class Renderer
{
	private lateinit var rootNode: TreeNode<*>

	fun renderRoot(element: UElement<*>)
	{
		check(!::rootNode.isInitialized) { "Already rendered" }
		rootNode = renderElement(element)
	}

	fun renderElement(element: UElement<*>) = element.toNode().rendered()

	private fun UElement<*>.toNode() = createComponent().attached().toNode()

	private fun UComponent<*>.attached() = also { attach { it.rendered() } }

	private fun UComponent<*>.toNode() = TreeNode(this)

	private fun UComponent<*>.rendered() = findComponentNode(this).rendered()

	private fun TreeNode<*>.rendered() = also { render(this@Renderer) }

	private fun findComponentNode(component: UComponent<*>) = rootNode.findNode(component) ?: throw IllegalArgumentException()
}
