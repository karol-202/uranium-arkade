package pl.karol202.uranium.core.internal

import pl.karol202.uranium.core.common.Detachable
import pl.karol202.uranium.core.component.Component

class TreeNode(private val component: Component) : Detachable
{
	private var children = emptyList<TreeNode>()

	fun findComponentNode(component: Component): TreeNode? =
			if(this.component == component) this
			else children.mapNotNull { it.findComponentNode(component) }.first()

	fun render(renderer: Renderer)
	{
		addChildren(component.render().map { renderer.renderElement(it) })
	}

	private fun addChildren(children: List<TreeNode>)
	{
		this.children += children
	}

	override fun detach()
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
