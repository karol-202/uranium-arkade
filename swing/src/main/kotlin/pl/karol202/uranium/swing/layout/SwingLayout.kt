package pl.karol202.uranium.swing.layout

import pl.karol202.uranium.swing.*
import java.awt.LayoutManager
import javax.swing.JPanel

abstract class SwingLayout<P : SwingChildrenProps>(props: P) : SwingComponent<P>(props)
{
	abstract class Props(key: Any,
	                     block: SwingBuilder.() -> Unit) : SwingChildrenProps(key, SwingBuilder().also(block).elements)

	private val container = JPanel()

	override val context = SwingContextImpl(container)

	override fun onAttach(parentContext: InvalidateableSwingContext) = parentContext.attachNative(container)

	override fun render() = props.children.also { onUpdate() }

	private fun onUpdate()
	{
		container.layout = createLayout()
	}

	protected abstract fun createLayout(): LayoutManager

	override fun onDetach(parentContext: InvalidateableSwingContext) = parentContext.detachNative(container)
}
