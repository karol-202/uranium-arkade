package pl.karol202.uranium.webcanvas.draw

import org.w3c.dom.HTMLCanvasElement
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.webcanvas.WCElement
import pl.karol202.uranium.webcanvas.WCRenderManager
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.native.WCNativeContainer
import pl.karol202.uranium.webcanvas.native.nativeContainer
import kotlin.browser.window

class CanvasRenderer(private val context: DrawContext,
                     private val renderInterval: Int,
                     elementProvider: WCRenderScope.() -> WCElement<*>)
{
	private val container = nativeContainer()
	private val renderManager = WCRenderManager(elementProvider.render(), container)

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

fun startOnCanvas(canvas: HTMLCanvasElement, renderInterval: Int, elementProvider: WCRenderScope.() -> WCElement<*>) =
		CanvasRenderer(canvas.also { it.fixBounds() }.context2d, renderInterval, elementProvider).start()
