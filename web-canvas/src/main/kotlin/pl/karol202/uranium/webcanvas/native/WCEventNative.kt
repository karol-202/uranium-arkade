package pl.karol202.uranium.webcanvas.native

import pl.karol202.uranium.webcanvas.values.InputEvent

class WCEventNativeContainer(private val transform: (InputEvent) -> InputEvent) : WCNativeContainer by nativeContainer()
{
	override fun handleEvent(event: InputEvent) = children.forEach { it.handleEvent(transform(event)) }
}

class WCEventNativeLeaf(private val listener: (InputEvent) -> Unit) : WCNative by nativeLeaf()
{
	override fun handleEvent(event: InputEvent) = listener(event)
}
