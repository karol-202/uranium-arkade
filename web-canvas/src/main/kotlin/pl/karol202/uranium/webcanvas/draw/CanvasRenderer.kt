package pl.karol202.uranium.webcanvas.draw

import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.events.MouseEvent
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.webcanvas.WCElement
import pl.karol202.uranium.webcanvas.WCRenderManager
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.native.WCNativeContainer
import pl.karol202.uranium.webcanvas.native.nativeContainer
import pl.karol202.uranium.webcanvas.values.InputEvent
import kotlin.browser.window

class CanvasRenderer(private val canvas: HTMLCanvasElement,
                     private val renderInterval: Int,
                     elementProvider: WCRenderScope.() -> WCElement<*>)
{
	private val container = nativeContainer()
	private val renderManager = WCRenderManager(elementProvider.render(), container)
	private val canvasContext = canvas.context2d

	private var timerHandle: Int? = null

	fun start()
	{
		initCanvas()
		renderManager.scheduleInit()
		timerHandle = window.setInterval(this::run, renderInterval)
	}

	private fun initCanvas()
	{
		canvas.fixBounds()
		canvas.onmousedown = ::handleMouseEvent
		canvas.onmousemove = ::handleMouseEvent
		canvas.onmouseup = ::handleMouseEvent
	}

	fun stop()
	{
		timerHandle?.let { window.clearInterval(it) }
	}

	private fun run()
	{
		canvasContext.clearRect(0.0, 0.0, canvas.width.toDouble(), canvas.height.toDouble())
		container.draw(canvasContext)
	}

	private fun handleMouseEvent(mouseEvent: MouseEvent) = container.handleEvent(InputEvent.from(mouseEvent))
}

fun startOnCanvas(canvas: HTMLCanvasElement, renderInterval: Int, elementProvider: WCRenderScope.() -> WCElement<*>) =
		CanvasRenderer(canvas, renderInterval, elementProvider).start()

