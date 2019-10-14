package pl.karol202.uranium.swing.frame

import pl.karol202.uranium.core.schedule.renderWithQueueScheduler
import pl.karol202.uranium.swing.SwingContextImpl
import pl.karol202.uranium.swing.util.SwingElement
import pl.karol202.uranium.swing.util.SwingRenderScope
import javax.swing.JFrame

abstract class SwingFrame
{
	companion object
	{
		fun withRoot(rootSupplier: SwingRenderScope.() -> SwingElement<*>) = SwingFrameBuilder(rootSupplier)
	}

	private val frame = JFrame()

	fun show()
	{
		render()
		frame.initFrame()
	}

	private fun render() = createRootElement().renderWithQueueScheduler(createContext())

	private fun createContext() = SwingContextImpl(frame)

	protected abstract fun createRootElement(): SwingElement<*>

	protected abstract fun JFrame.initFrame()
}
