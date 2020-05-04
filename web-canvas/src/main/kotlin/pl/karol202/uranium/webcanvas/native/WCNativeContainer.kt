package pl.karol202.uranium.webcanvas.native

import pl.karol202.uranium.core.native.UNative
import pl.karol202.uranium.core.native.UNativeContainer
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.draw.DrawContext
import pl.karol202.uranium.webcanvas.draw.DrawOperation

class WCNativeContainer(private val beforeDrawOperation: DrawOperation = { },
                        private val afterDrawOperation: DrawOperation = { }) : WCNative, UNativeContainer<WC>
{
	private val children = mutableListOf<WCNative>()

	override fun attach(native: UNative<WC>, index: Int)
	{
		children.add(index, native.asWCNative)
	}

	override fun detach(native: UNative<WC>)
	{
		children.remove(native)
	}

	override fun draw(context: DrawContext)
	{
		context.beforeDrawOperation()
		children.forEach { it.draw(context) }
		context.afterDrawOperation()
	}
}
