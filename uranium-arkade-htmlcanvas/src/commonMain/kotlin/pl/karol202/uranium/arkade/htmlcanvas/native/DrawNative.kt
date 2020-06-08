package pl.karol202.uranium.arkade.htmlcanvas.native

import pl.karol202.uranium.arkade.htmlcanvas.draw.DrawContext
import pl.karol202.uranium.arkade.htmlcanvas.draw.DrawOperation

internal class ArkadeDrawNativeContainer(private val beforeDrawOperation: DrawOperation = { },
                                         private val afterDrawOperation: DrawOperation = { }) :
		ArkadeNativeContainer by nativeContainer()
{
	override fun draw(context: DrawContext)
	{
		context.beforeDrawOperation()
		children.forEach { it.draw(context) }
		context.afterDrawOperation()
	}
}

internal class ArkadeDrawNativeLeaf(private val drawOperation: DrawOperation) : ArkadeNative by nativeLeaf()
{
	override fun draw(context: DrawContext) = context.drawOperation()
}
