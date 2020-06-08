package pl.karol202.uranium.arkade.htmlcanvas.native

import pl.karol202.uranium.arkade.htmlcanvas.values.InputEvent

internal class ArkadeEventNativeContainer(private val transform: (InputEvent) -> InputEvent) :
		ArkadeNativeContainer by nativeContainer()
{
	override fun handleEvent(event: InputEvent)
	{
		val transformedEvent = transform(event)
		children.forEach { it.handleEvent(transformedEvent) }
	}
}

internal class ArkadeEventNativeLeaf(private val listener: (InputEvent) -> Unit) : ArkadeNative by nativeLeaf()
{
	override fun handleEvent(event: InputEvent) = listener(event)
}
