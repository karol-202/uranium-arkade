package pl.karol202.uranium.swing.control

import pl.karol202.uranium.swing.SwingComponent
import pl.karol202.uranium.swing.SwingContext
import pl.karol202.uranium.swing.SwingElement

abstract class SwingControl<P : SwingComponent.Props>(props: P) : SwingComponent<P>(props)
{
	override val context: SwingContext? = null

	override fun renderChildren() = emptyList<SwingElement<*>>()
}
