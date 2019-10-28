package pl.karol202.uranium.swing.frame

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import pl.karol202.uranium.core.schedule.renderToNode
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

	private fun render() = scheduler.renderToNode(renderRoot(), createContext())

	protected abstract fun renderRoot(): SwingElement<*>

	private fun createContext() = SwingContextImpl(frame)

	protected abstract fun JFrame.initFrame()
}
