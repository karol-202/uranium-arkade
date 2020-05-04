package pl.karol202.uranium.webcanvas.native

import pl.karol202.uranium.webcanvas.render.DrawContext
import pl.karol202.uranium.webcanvas.render.DrawOperation

class WCNativeLeaf(private val drawOperation: DrawOperation) : WCNative
{
	override fun draw(context: DrawContext) = context.drawOperation()
}
