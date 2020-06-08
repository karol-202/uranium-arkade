package pl.karol202.uranium.arkade.htmlcanvas.native

import pl.karol202.uranium.arkade.htmlcanvas.physics.PhysicsContext

internal class ArkadePhysicsNativeContainer(private val transform: (PhysicsContext) -> PhysicsContext) :
		ArkadeNativeContainer by nativeContainer()
{
	override fun performPhysics(context: PhysicsContext)
	{
		val transformedContext = transform(context)
		children.forEach { it.performPhysics(transformedContext) }
	}
}

internal class ArkadePhysicsNativeLeaf(private val listener: (PhysicsContext) -> Unit) : ArkadeNative by nativeLeaf()
{
	override fun performPhysics(context: PhysicsContext) = listener(context)
}
