package pl.karol202.uranium.core.internal

import pl.karol202.uranium.core.common.Detachable
import pl.karol202.uranium.core.common.HasKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.UComponent
import pl.karol202.uranium.core.element.UElement

class TreeNode<P : UProps>(private val component: UComponent<P>) : Detachable, HasKey
{
	private var children = emptyList<TreeNode<*>>()

	override val key get() = component.key

	fun findNode(component: UComponent<*>): TreeNode<*>? =
			if(this.component == component) this
			else children.mapNotNull { it.findNode(component) }.first()

	fun render(renderer: Renderer)
	{
		val newElements = component.render()
		val newKeys = newElements.map { it.key }
		detachOldChildren(newKeys)
		children = newElements.map { it.reuseOrRenderElement(renderer) }
	}

	private fun detachOldChildren(newKeys: List<Any>) = children.filter { it.key !in newKeys }.forEach { it.detach() }

	private fun UElement<*>.reuseOrRenderElement(renderer: Renderer) = reuse(renderer) ?: render(renderer)

	private fun <P : UProps> UElement<P>.reuse(renderer: Renderer) = findChildrenWithKey<P>(key)?.updated(this, renderer)

	private fun <P : UProps> findChildrenWithKey(key: Any) = children.firstOrNull { it.key == key } as? TreeNode<P>

	private fun updated(element: UElement<P>, renderer: Renderer) =
			if(needsRerender(element)) withNewProps(element).rendered(renderer)
			else withNewProps(element)

	private fun needsRerender(element: UElement<P>) = component.props == element.props

	private fun withNewProps(element: UElement<P>) = also { component.props = element.props }

	private fun rendered(renderer: Renderer) = also { render(renderer) }

	private fun UElement<*>.render(renderer: Renderer): TreeNode<*> = renderer.renderElement(this)

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
