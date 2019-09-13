package pl.karol202.uranium.swing.frame

import pl.karol202.uranium.swing.SwingRenderBuilder
import pl.karol202.uranium.swing.SwingContextImpl
import pl.karol202.uranium.swing.SwingElement
import pl.karol202.uranium.swing.SwingRenderer
import javax.swing.JFrame

abstract class SwingFrame
{
	companion object
	{
		fun withRoot(rootSupplier: SwingRenderBuilder.() -> SwingElement<*>) = SwingFrameBuilder(rootSupplier)
	}

	protected abstract val SwingRenderBuilder.rootElement: SwingElement<*>

	private val frame = JFrame()

	fun show()
	{
		render()
		frame.initFrame()
	}

	private fun render() = SwingRenderer().renderRoot(createRootElement(), createContext())

	private fun createRootElement() = SwingRenderBuilder().rootElement

	private fun createContext() = SwingContextImpl(frame)

	protected abstract fun JFrame.initFrame()
}
