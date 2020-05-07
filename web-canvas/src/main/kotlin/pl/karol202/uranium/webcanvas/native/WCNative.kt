package pl.karol202.uranium.webcanvas.native

import pl.karol202.uranium.core.native.UNative
import pl.karol202.uranium.core.native.UNativeContainer
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.draw.DrawContext
import pl.karol202.uranium.webcanvas.physics.PhysicsContext
import pl.karol202.uranium.webcanvas.values.InputEvent

private val UNative<WC>.asWCNative get() = this as WCNative

interface WCNative : UNative<WC>
{
	fun draw(context: DrawContext)

	fun handleEvent(event: InputEvent)

	fun performPhysics(context: PhysicsContext)
}

interface WCNativeContainer : WCNative, UNativeContainer<WC>
{
	val children: List<WCNative>
}

fun nativeLeaf() = object : WCNative {

	override fun draw(context: DrawContext) { }

	override fun handleEvent(event: InputEvent) { }

	override fun performPhysics(context: PhysicsContext) { }
}

fun nativeContainer() = object : WCNativeContainer {

	override val children = mutableListOf<WCNative>()

	override fun attach(native: UNative<WC>, index: Int)
	{
		children.add(index, native.asWCNative)
	}

	override fun detach(native: UNative<WC>)
	{
		children.remove(native)
	}

	override fun draw(context: DrawContext) = children.forEach { it.draw(context) }

	override fun handleEvent(event: InputEvent) = children.forEach { it.handleEvent(event) }

	override fun performPhysics(context: PhysicsContext) = children.forEach { it.performPhysics(context) }
}
