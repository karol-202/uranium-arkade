package pl.karol202.uranium.swing

import pl.karol202.uranium.core.context.UContext
import java.awt.Component
import java.awt.Container

class SwingContextImpl(private val container: Container) : UContext<Component>
{
	override fun attachNative(nativeComponent: Component)
	{
		container.add(nativeComponent)
	}

	override fun detachNative(nativeComponent: Component) = container.remove(nativeComponent)
}
