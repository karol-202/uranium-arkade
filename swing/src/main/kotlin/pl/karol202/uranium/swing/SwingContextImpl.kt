package pl.karol202.uranium.swing

class SwingContextImpl(private val container: SwingContainer) : SwingContext
{
	override fun attachNative(nativeComponent: SwingNative)
	{
		container.add(nativeComponent)
	}

	override fun detachNative(nativeComponent: SwingNative) = container.remove(nativeComponent)
}
