package pl.karol202.uranium.core.internal

import pl.karol202.uranium.core.context.Context
import pl.karol202.uranium.core.component.UComponent
import pl.karol202.uranium.core.element.UElement

class Renderer<C : Context<*>>
{
	private lateinit var rootNode: TreeNode<C, *>

	fun renderRoot(element: UElement<C, *>, context: C)
	{
		check(!::rootNode.isInitialized) { "Already rendered" }
		rootNode = renderElement(element, context)
	}

	fun renderElement(element: UElement<C, *>, context: C) = element.toNode().rendered()

	private fun UElement<C, *>.toNode() = createComponent().attached().toNode()

	private fun UComponent<C, *>.attached() = also { attach() }

	private fun UComponent<C, *>.toNode() = TreeNode(this)

	private fun UComponent<C, *>.rendered() = findComponentNode(this).rendered()

	private fun TreeNode<C, *>.rendered() = also { render(this@Renderer) }

	private fun findComponentNode(component: UComponent<C, *>) = rootNode.findNode(component) ?: throw IllegalArgumentException()
}
