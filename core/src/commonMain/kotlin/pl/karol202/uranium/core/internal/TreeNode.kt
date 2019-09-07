package pl.karol202.uranium.core.internal

import pl.karol202.uranium.core.common.Detachable
import pl.karol202.uranium.core.common.KeyProvider
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.UComponent
import pl.karol202.uranium.core.element.UElement

class TreeNode<N, P : UProps>(private val component: UComponent<N, P>) : Detachable, KeyProvider
{
	private var children = emptyList<TreeNode<N, *>>()

	override val key get() = component.key

	fun findNode(component: UComponent<N, *>): TreeNode<N, *>? =
			if(this.component == component) this
			else children.mapNotNull { it.findNode(component) }.first()

	fun render(renderer: Renderer<N>)
	{
		val newElements = component.render()
		val newKeys = newElements.map { it.key }
		detachOldChildren(newKeys)
		updateChildren(renderer, newElements)
	}

	private fun detachOldChildren(newKeys: List<Any>) = children.filter { it.key !in newKeys }.forEach { it.detach() }

	private fun updateChildren(renderer: Renderer<N>, newElements: List<UElement<N, *>>)
	{
		children = newElements.map { it.reuseOrRenderElement(renderer) }
	}

	private fun UElement<N, *>.reuseOrRenderElement(renderer: Renderer<N>) = reuse(renderer) ?: render(renderer)

	private fun UElement<N, *>.render(renderer: Renderer<N>): TreeNode<N, *> = renderer.renderElement(this, component.requireContext())

	private fun <P : UProps> UElement<N, P>.reuse(renderer: Renderer<N>) = findChildrenWithKey<P>(key)?.updated(this, renderer)

	@Suppress("UNCHECKED_CAST")
	private fun <P : UProps> findChildrenWithKey(key: Any) = children.firstOrNull { it.key == key } as? TreeNode<N, P>

	private fun updated(element: UElement<N, P>, renderer: Renderer<N>) =
			if(needsRerender(element)) withNewProps(element).rendered(renderer)
			else withNewProps(element)

	// Returning false doesn't have to necessarily mean that props haven't changed
	private fun needsRerender(element: UElement<N, P>) = component.props == element.props

	private fun withNewProps(element: UElement<N, P>) = also { component.modifyPropsInternal(element.props) }

	private fun rendered(renderer: Renderer<N>) = also { render(renderer) }

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
