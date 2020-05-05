package pl.karol202.uranium.webcanvas.native

import pl.karol202.uranium.core.native.UNative
import pl.karol202.uranium.core.native.UNativeContainer
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.draw.DrawContext

val UNative<WC>.asWCNative get() = this as WCNative

interface WCNative : UNative<WC>
{
	fun draw(context: DrawContext)
}

interface WCNativeContainer : WCNative, UNativeContainer<WC>
{
	val children: List<WCNative>
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
}
