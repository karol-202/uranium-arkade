package pl.karol202.uranium.swing.control

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.swing.*

abstract class SwingControl<P : UProps>(props: P) : SwingComponent<P>(props)
{
	protected abstract val control: SwingNative

	override val context: SwingContext? = null

	override fun onAttach(parentContext: InvalidateableSwingContext) = parentContext.attachNative(control)

	override fun render() = emptyList<SwingElement<*>>().also { onUpdate() }

	protected open fun onUpdate() { }

	override fun onDetach(parentContext: InvalidateableSwingContext) = parentContext.detachNative(control)
}
