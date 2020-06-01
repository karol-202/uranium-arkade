package pl.karol202.uranium.swing.frame

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.manager.RenderManager
import pl.karol202.uranium.core.manager.queueRenderScheduler
import pl.karol202.uranium.swing.native.SwingNative
import pl.karol202.uranium.swing.SwingElement
import pl.karol202.uranium.swing.SwingRenderManager
import pl.karol202.uranium.swing.SwingRenderScope
import javax.swing.JFrame

abstract class SwingFrame
{
	companion object
	{
		fun withRoot(rootSupplier: SwingRenderScope.() -> SwingElement<*>) = SwingFrameBuilder(rootSupplier)
	}

	private val frame = JFrame()
	private val container = SwingNative.container(frame.contentPane)
	private var renderManager: SwingRenderManager<*>? = null

	fun show()
	{
		render()
		frame.initFrame()
	}

	private fun render() = getOrCreateRenderManager(renderRoot()).scheduleInit()

	private fun <P : UProps> getOrCreateRenderManager(element: SwingElement<P>) =
			renderManager ?: RenderManager(element, container, queueRenderScheduler()).also { renderManager = it }

	protected abstract fun renderRoot(): SwingElement<*>

	protected abstract fun JFrame.initFrame()
}
