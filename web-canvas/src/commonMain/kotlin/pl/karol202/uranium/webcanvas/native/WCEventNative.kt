package pl.karol202.uranium.webcanvas.native

import pl.karol202.uranium.webcanvas.input.InputEvent

class WCEventNativeContainer(private val transform: (InputEvent) -> InputEvent) : WCNativeContainer by nativeContainer()
{
	override fun handleEvent(event: InputEvent)
	{
		val transformedEvent = transform(event)
		children.forEach { it.handleEvent(transformedEvent) }
	}
}

class WCEventNativeLeaf(private val listener: (InputEvent) -> Unit) : WCNative by nativeLeaf()
{
	override fun handleEvent(event: InputEvent) = listener(event)
}
