package pl.karol202.uranium.webcanvas.draw

import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.events.MouseEvent
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.webcanvas.WCElement
import pl.karol202.uranium.webcanvas.WCRenderManager
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.native.nativeContainer
import pl.karol202.uranium.webcanvas.physics.PhysicsContext
import pl.karol202.uranium.webcanvas.values.InputEvent
import kotlin.browser.window
import kotlin.time.Duration
import kotlin.time.milliseconds

class CanvasManager(private val canvas: HTMLCanvasElement,
                    private val renderInterval: Int,
                    private val physicsInterval: Int,
                    elementProvider: WCRenderScope.() -> WCElement<*>)
{
	private val canvasContext = canvas.context2d
	private val container = nativeContainer()
	private val renderManager by lazy { WCRenderManager(elementProvider.render(), container) }

	private var renderTimer: Int? = null
	private var physicsTimer: Int? = null

	private var lastPhysicsTime: Double? = null

	fun start()
	{
		initCanvas()
		renderManager.scheduleInit()
		renderTimer = window.setInterval(this::draw, renderInterval)
		physicsTimer = window.setInterval(this::performPhysics, physicsInterval)
	}

	private fun initCanvas()
	{
		canvas.fixBounds()
		canvas.onmousedown = ::handleMouseEvent
		canvas.onmousemove = ::handleMouseEvent
		canvas.onmouseup = ::handleMouseEvent
		canvas.onmouseenter = ::handleMouseEvent
		canvas.onmouseleave = ::handleMouseEvent
	}

	fun stop()
	{
		renderTimer?.let { window.clearInterval(it) }
		physicsTimer?.let { window.clearInterval(it) }
	}

	private fun draw()
	{
		canvasContext.clearCanvas()
		container.draw(canvasContext)
	}

	private fun performPhysics() = container.performPhysics(createPhysicsContext())

	private fun createPhysicsContext() = PhysicsContext(calculateDeltaTime())

	private fun calculateDeltaTime(): Duration
	{
		val currentTime = window.performance.now()
		val lastTime = lastPhysicsTime ?: currentTime
		lastPhysicsTime = currentTime
		return (currentTime - lastTime).milliseconds
	}

	private fun handleMouseEvent(mouseEvent: MouseEvent) = container.handleEvent(InputEvent.from(mouseEvent))
}

fun startOnCanvas(canvas: HTMLCanvasElement,
                  renderInterval: Int,
                  physicsInterval: Int,
                  elementProvider: WCRenderScope.() -> WCElement<*>) =
		CanvasManager(canvas, renderInterval, physicsInterval, elementProvider).start()

