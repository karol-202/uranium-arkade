package pl.karol202.uranium.arkade.htmlcanvas.native

import pl.karol202.uranium.arkade.htmlcanvas.Arkade
import pl.karol202.uranium.arkade.htmlcanvas.draw.DrawContext
import pl.karol202.uranium.arkade.htmlcanvas.physics.PhysicsContext
import pl.karol202.uranium.arkade.htmlcanvas.physics.collider.Collider
import pl.karol202.uranium.arkade.htmlcanvas.values.InputEvent
import pl.karol202.uranium.core.native.UNative
import pl.karol202.uranium.core.native.UNativeContainer
import pl.karol202.uranium.core.util.NativeList
import pl.karol202.uranium.core.util.emptyNativeList

private val UNative<Arkade>.asArkadeNative get() = this as ArkadeNative

internal interface ArkadeNative : UNative<Arkade>
{
	fun draw(context: DrawContext)

	fun handleEvent(event: InputEvent)

	fun performPhysics(context: PhysicsContext)

	fun collectColliders(): NativeList<Collider>
}

internal interface ArkadeNativeContainer : ArkadeNative, UNativeContainer<Arkade>
{
	val children: NativeList<ArkadeNative>
}

internal fun nativeLeaf() = object : ArkadeNative {

	override fun draw(context: DrawContext) { }

	override fun handleEvent(event: InputEvent) { }

	override fun performPhysics(context: PhysicsContext) { }

	override fun collectColliders() = emptyNativeList<Collider>()
}

internal fun nativeContainer() = object : ArkadeNativeContainer {

	override var children = emptyNativeList<ArkadeNative>()

	override fun attach(native: UNative<Arkade>, index: Int)
	{
		children = children.inserted(native.asArkadeNative, index)
	}

	override fun detach(native: UNative<Arkade>)
	{
		children -= native.asArkadeNative
	}

	override fun draw(context: DrawContext) = children.forEach { it.draw(context) }

	override fun handleEvent(event: InputEvent) = children.forEach { it.handleEvent(event) }

	override fun performPhysics(context: PhysicsContext) = children.forEach { it.performPhysics(context) }

	override fun collectColliders() = children.flatMap { it.collectColliders() }
}
