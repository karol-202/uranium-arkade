package pl.karol202.uranium.swing.layout

import pl.karol202.uranium.core.element.UElement
import pl.karol202.uranium.swing.SwingRenderBuilder
import pl.karol202.uranium.swing.SwingComponent
import pl.karol202.uranium.swing.SwingContextImpl
import pl.karol202.uranium.swing.SwingElement
import pl.karol202.uranium.swing.util.BaseListeners
import java.awt.LayoutManager
import javax.swing.JPanel

abstract class SwingLayout<P : SwingLayout.Props>(props: P) : SwingComponent<P>(props)
{
	open class Props(key: Any,
	                 baseListeners: BaseListeners,
	                 enabled: Boolean,
	                 visible: Boolean,
	                 val children: List<SwingElement<*>>) : SwingComponent.Props(key, baseListeners, enabled, visible)

	final override val native = JPanel()

	override val context = SwingContextImpl(native)

	override fun renderChildren() = props.children

	override fun onUpdate()
	{
		super.onUpdate()
		native.layout = createLayout()
	}

	protected abstract fun createLayout(): LayoutManager
}
