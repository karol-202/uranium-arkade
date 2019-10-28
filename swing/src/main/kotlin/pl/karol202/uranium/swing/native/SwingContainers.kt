package pl.karol202.uranium.swing.native

import java.awt.*
import javax.swing.BoxLayout
import javax.swing.OverlayLayout

val Container.componentsWithConstraints get() = components.map { it to layout.getLayoutConstraints(it) }

fun LayoutManager.getLayoutConstraints(component: Component) = when(this)
{
	is BorderLayout -> getConstraints(component)
	is GridBagLayout -> getConstraints(component)
	is BoxLayout, is FlowLayout, is GridLayout, is OverlayLayout -> null
	else -> throw RuntimeException("Unsupported layout: $this")
}
