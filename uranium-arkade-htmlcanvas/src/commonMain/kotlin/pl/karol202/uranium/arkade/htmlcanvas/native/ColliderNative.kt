package pl.karol202.uranium.arkade.htmlcanvas.native

import pl.karol202.uranium.arkade.htmlcanvas.physics.collider.Collider
import pl.karol202.uranium.core.util.nativeListOf

internal class ColliderNativeContainer(private val transform: (Collider) -> Collider) :
		ArkadeNativeContainer by nativeContainer()
{
	override fun collectColliders() = children.flatMap { it.collectColliders() }.map(transform)
}

internal class ColliderNativeLeaf(private val colliderProvider: () -> Collider) : ArkadeNative by nativeLeaf()
{
	override fun collectColliders() = nativeListOf(colliderProvider())
}
