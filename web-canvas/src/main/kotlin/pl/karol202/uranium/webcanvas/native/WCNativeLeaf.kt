package pl.karol202.uranium.webcanvas.native

import pl.karol202.uranium.webcanvas.draw.DrawContext
import pl.karol202.uranium.webcanvas.draw.DrawOperation

class WCNativeLeaf(private val drawOperation: DrawOperation) : WCNative
{
	override fun draw(context: DrawContext) = context.drawOperation()
}
