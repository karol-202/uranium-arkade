package pl.karol202.uranium.swing

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.UElement
import java.awt.Component

abstract class ControlSwingComponent<P : UProps>(props: P) : SwingComponent<P>(props)
{
	override val context: SwingContext? = null

	override fun render() = emptyList<UElement<Component, *>>().also { onUpdate() }

	open fun onUpdate() { }
}
