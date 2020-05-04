package pl.karol202.uranium.webcanvas.render

import pl.karol202.uranium.webcanvas.WCElement
import pl.karol202.uranium.webcanvas.WCRenderManager
import pl.karol202.uranium.webcanvas.native.WCNativeContainer
import kotlin.browser.window

class CanvasRenderer(private val context: DrawContext,
                     private val renderInterval: Int,
                     element: WCElement<*>)
{
	private val container = WCNativeContainer()
	private val renderManager = WCRenderManager(element, container)

	private var timerHandle: Int? = null

	fun start()
	{
		renderManager.scheduleInit()
		timerHandle = window.setInterval(this::run, renderInterval)
	}

	fun stop()
	{
		timerHandle?.let { window.clearInterval(it) }
	}

	private fun run() = container.draw(context)
}
