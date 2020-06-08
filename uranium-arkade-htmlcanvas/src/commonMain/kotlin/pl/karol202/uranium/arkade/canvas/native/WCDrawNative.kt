package pl.karol202.uranium.arkade.canvas.native

import pl.karol202.uranium.arkade.canvas.draw.DrawContext
import pl.karol202.uranium.arkade.canvas.draw.DrawOperation

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
