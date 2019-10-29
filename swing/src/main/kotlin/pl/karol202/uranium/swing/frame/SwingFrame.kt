package pl.karol202.uranium.swing.frame

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import pl.karol202.uranium.core.native.asNativeNode
import pl.karol202.uranium.core.tree.createNode
import pl.karol202.uranium.swing.native.SwingNative
import pl.karol202.uranium.swing.util.SwingElement
import pl.karol202.uranium.swing.util.SwingRenderScope
import pl.karol202.uranium.swing.util.SwingSuspendRenderScheduler
import javax.swing.JFrame

abstract class SwingFrame
{
	companion object
	{
		fun withRoot(rootSupplier: SwingRenderScope.() -> SwingElement<*>) = SwingFrameBuilder(rootSupplier)
	}

	private val frame = JFrame()
	private val coroutineScope = CoroutineScope(Dispatchers.Main)
	private val scheduler = SwingSuspendRenderScheduler(coroutineScope)

	fun show()
	{
		render()
		frame.initFrame()
	}

	private fun render()
	{
		val node = renderRoot().createNode(scheduler)
		node.scheduleInit()
		scheduler.submit {
			SwingNative.fromContainer(frame.contentPane).asNativeNode().commit(node.nativeNodes)
		}
	}

	protected abstract fun renderRoot(): SwingElement<*>

	protected abstract fun JFrame.initFrame()
}
