package pl.karol202.uranium.core.internal

import pl.karol202.uranium.core.component.Component
import pl.karol202.uranium.core.element.Element

class Renderer
{
	private lateinit var rootNode: TreeNode<*>

	fun renderRoot(element: Element<*>)
	{
		check(!::rootNode.isInitialized) { "Already rendered" }
		rootNode = renderElement(element)
	}

	fun renderElement(element: Element<*>) = element.toNode().rendered()

	private fun Element<*>.toNode() = createComponent().attached().toNode()

	private fun Component<*>.attached() = also { attach { it.rendered() } }

	private fun Component<*>.toNode() = TreeNode(this)

	private fun Component<*>.rendered() = findComponentNode(this).rendered()

	private fun TreeNode<*>.rendered() = also { render(this@Renderer) }

	private fun findComponentNode(component: Component<*>) = rootNode.findNode(component) ?: throw IllegalArgumentException()
}
