package pl.karol202.uranium.swing.frame

import pl.karol202.uranium.swing.SwingBuilder
import pl.karol202.uranium.swing.SwingContextImpl
import pl.karol202.uranium.swing.SwingElement
import pl.karol202.uranium.swing.SwingRenderer
import javax.swing.JFrame

abstract class SwingFrame
{
	companion object
	{
		fun withRoot(rootSupplier: SwingBuilder.() -> SwingElement<*>) = SwingFrameBuilder(rootSupplier)
	}

	protected abstract val SwingBuilder.rootElement: SwingElement<*>

	private val frame = JFrame()

	fun show()
	{
		render()
		frame.initFrame()
	}

	private fun render() = SwingRenderer().renderRoot(createRootElement(), createContext())

	private fun createRootElement() = SwingBuilder().rootElement

	private fun createContext() = SwingContextImpl(frame)

	protected abstract fun JFrame.initFrame()
}
