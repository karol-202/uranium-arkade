package pl.karol202.uranium.webcanvas.native

import pl.karol202.uranium.webcanvas.draw.DrawContext
import pl.karol202.uranium.webcanvas.draw.DrawOperation

class WCDrawNativeContainer(private val beforeDrawOperation: DrawOperation = { },
                            private val afterDrawOperation: DrawOperation = { }) : WCNativeContainer by nativeContainer()
{
	override fun draw(context: DrawContext)
	{
		context.beforeDrawOperation()
		children.forEach { it.draw(context) }
		context.afterDrawOperation()
	}
}

class WCDrawNativeLeaf(private val drawOperation: DrawOperation) : WCNative by nativeLeaf()
{
	override fun draw(context: DrawContext) = context.drawOperation()
}
