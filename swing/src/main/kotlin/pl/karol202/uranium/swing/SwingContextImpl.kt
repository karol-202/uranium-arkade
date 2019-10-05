package pl.karol202.uranium.swing

import pl.karol202.uranium.swing.util.SwingContainer
import pl.karol202.uranium.swing.util.SwingContext

class SwingContextImpl(private val container: SwingContainer) : SwingContext
{
	override fun attachNative(native: SwingNativeWrapper) = container.add(native.component, native.constraints)

	override fun detachNative(native: SwingNativeWrapper) = container.remove(native.component)
}
