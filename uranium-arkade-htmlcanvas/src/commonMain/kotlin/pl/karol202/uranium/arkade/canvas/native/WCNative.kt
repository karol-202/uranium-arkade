package pl.karol202.uranium.arkade.canvas.native

import pl.karol202.uranium.arkade.canvas.WC
import pl.karol202.uranium.arkade.canvas.draw.DrawContext
import pl.karol202.uranium.arkade.canvas.physics.PhysicsContext
import pl.karol202.uranium.arkade.canvas.physics.collider.Collider
import pl.karol202.uranium.arkade.canvas.values.InputEvent
import pl.karol202.uranium.core.native.UNative
import pl.karol202.uranium.core.native.UNativeContainer
import pl.karol202.uranium.core.util.NativeList
import pl.karol202.uranium.core.util.emptyNativeList

private val UNative<WC>.asWCNative get() = this as WCNative

interface WCNative : UNative<WC>
{
	fun draw(context: DrawContext)

	fun handleEvent(event: InputEvent)

	fun performPhysics(context: PhysicsContext)

	fun collectColliders(): NativeList<Collider>
}

interface WCNativeContainer : WCNative, UNativeContainer<WC>
{
	val children: NativeList<WCNative>
}

fun nativeLeaf() = object : WCNative {

	override fun draw(context: DrawContext) { }

	override fun handleEvent(event: InputEvent) { }

	override fun performPhysics(context: PhysicsContext) { }

	override fun collectColliders() = emptyNativeList<Collider>()
}

fun nativeContainer() = object : WCNativeContainer {

	override var children = emptyNativeList<WCNative>()

	override fun attach(native: UNative<WC>, index: Int)
	{
		children = children.inserted(native.asWCNative, index)
	}

	override fun detach(native: UNative<WC>)
	{
		children -= native.asWCNative
	}

	override fun draw(context: DrawContext) = children.forEach { it.draw(context) }

	override fun handleEvent(event: InputEvent) = children.forEach { it.handleEvent(event) }

	override fun performPhysics(context: PhysicsContext) = children.forEach { it.performPhysics(context) }

	override fun collectColliders() = children.flatMap { it.collectColliders() }
}
