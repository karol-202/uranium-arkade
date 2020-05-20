package pl.karol202.uranium.webcanvas.native

import pl.karol202.uranium.webcanvas.physics.PhysicsContext

class WCPhysicsNativeContainer(private val transform: (PhysicsContext) -> PhysicsContext) : WCNativeContainer by nativeContainer()
{
	override fun performPhysics(context: PhysicsContext)
	{
		val transformedContext = transform(context)
		children.forEach { it.performPhysics(transformedContext) }
	}
}

class WCPhysicsNativeLeaf(private val listener: (PhysicsContext) -> Unit) : WCNative by nativeLeaf()
{
	override fun performPhysics(context: PhysicsContext) = listener(context)
}
