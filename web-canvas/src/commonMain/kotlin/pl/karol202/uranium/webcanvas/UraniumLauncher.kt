package pl.karol202.uranium.webcanvas

import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.webcanvas.draw.clearViewport
import pl.karol202.uranium.webcanvas.draw.getNativeCanvas
import pl.karol202.uranium.webcanvas.native.nativeContainer
import pl.karol202.uranium.webcanvas.physics.PhysicsContext
import pl.karol202.uranium.webcanvas.input.InputEvent
import pl.karol202.uranium.webcanvas.input.setKeyboardEventListener
import pl.karol202.uranium.webcanvas.input.setMouseEventListener
import pl.karol202.uranium.webcanvas.util.clearInterval
import pl.karol202.uranium.webcanvas.util.now
import pl.karol202.uranium.webcanvas.util.setInterval
import kotlin.time.Duration
import kotlin.time.milliseconds

class UraniumLauncher(private val canvasId: String,
                      private val renderInterval: Int,
                      private val physicsInterval: Int,
                      elementProvider: WCRenderScope.() -> WCElement<*>)
{
	private val canvas = getNativeCanvas(canvasId)
	private val drawContext = canvas.context
	private val container = nativeContainer()
	private val renderManager by lazy { WCRenderManager(elementProvider.render(), container) }

	private var renderTimer: Int? = null
	private var physicsTimer: Int? = null

	private var lastPhysicsTime: Double? = null

	fun start()
	{
		initContext()
		initEventListeners()
		renderManager.scheduleInit()
		renderTimer = setInterval(renderInterval, this::draw)
		physicsTimer = setInterval(physicsInterval, this::performPhysics)
	}

	private fun initContext()
	{
		canvas.width = canvas.clientWidth
		canvas.height = canvas.clientHeight
	}

	private fun initEventListeners()
	{
		setMouseEventListener(canvasId, ::handleEvent)
		setKeyboardEventListener(::handleEvent)
	}

	fun stop()
	{
		renderTimer?.let { clearInterval(it) }
		physicsTimer?.let { clearInterval(it) }
	}

	private fun draw()
	{
		drawContext.clearViewport()
		container.draw(drawContext)
	}

	private fun performPhysics() = container.performPhysics(createPhysicsContext())

	private fun createPhysicsContext() = PhysicsContext(calculateDeltaTime())

	private fun calculateDeltaTime(): Duration
	{
		val currentTime = now()
		val lastTime = lastPhysicsTime ?: currentTime
		lastPhysicsTime = currentTime
		return (currentTime - lastTime).milliseconds
	}

	private fun handleEvent(event: InputEvent) = container.handleEvent(event)
}

fun startOnCanvas(canvasId: String,
                  renderInterval: Int,
                  physicsInterval: Int,
                  elementProvider: WCRenderScope.() -> WCElement<*>) =
		UraniumLauncher(canvasId, renderInterval, physicsInterval, elementProvider).start()

