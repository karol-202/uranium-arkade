package pl.karol202.uranium.webcanvas.native

import pl.karol202.uranium.core.util.nativeListOf
import pl.karol202.uranium.webcanvas.physics.collider.Collider

class WCColliderNativeContainer(private val transform: (Collider) -> Collider) : WCNativeContainer by nativeContainer()
{
	override fun collectColliders() = children.flatMap { it.collectColliders() }.map(transform)
}

class WCColliderNativeLeaf(private val colliderProvider: () -> Collider) : WCNative by nativeLeaf()
{
	override fun collectColliders() = nativeListOf(colliderProvider())
}
