package pl.karol202.uranium.arkade.htmlcanvas

import pl.karol202.uranium.arkade.htmlcanvas.dom.canvas.getNativeCanvas
import pl.karol202.uranium.arkade.htmlcanvas.dom.input.NativeKeyboardEvent
import pl.karol202.uranium.arkade.htmlcanvas.dom.input.NativeMouseEvent
import pl.karol202.uranium.arkade.htmlcanvas.dom.window.NativeWindow
import pl.karol202.uranium.arkade.htmlcanvas.draw.asDrawContext
import pl.karol202.uranium.arkade.htmlcanvas.native.nativeContainer
import pl.karol202.uranium.arkade.htmlcanvas.physics.PhysicsContext
import pl.karol202.uranium.arkade.htmlcanvas.scheduler.createRenderScheduler
import pl.karol202.uranium.arkade.htmlcanvas.values.InputEvent
import pl.karol202.uranium.arkade.htmlcanvas.values.Vector
import pl.karol202.uranium.core.render.render
import kotlin.time.Duration
import kotlin.time.milliseconds

class UraniumLauncher(canvasId: String,
                      private val renderInterval: Int,
                      private val physicsInterval: Int,
                      private val elementProvider: ArkadeRenderScope.(size: Vector) -> ArkadeElement<*>)
{
	private val canvas = getNativeCanvas(canvasId)
	private val canvasContext = canvas.context.asDrawContext
	private val container = nativeContainer()

	private var renderTimer: Int? = null
	private var physicsTimer: Int? = null

	private var lastPhysicsTime: Double? = null

	fun start()
	{
		initContext()
		initEventListeners()
		initTimers()
		createRenderManager().scheduleInit()
	}

	private fun initContext()
	{
		canvasContext.size = canvasContext.clientSize
	}

	private fun initEventListeners()
	{
		canvas.setOnMouseDownListener(::handleMouseEvent)
		canvas.setOnMouseMoveListener(::handleMouseEvent)
		canvas.setOnMouseUpListener(::handleMouseEvent)
		canvas.setOnMouseEnterListener(::handleMouseEvent)
		canvas.setOnMouseLeaveListener(::handleMouseEvent)
		NativeWindow.setOnKeyDownListener(::handleKeyEvent)
		NativeWindow.setOnKeyPressListener(::handleKeyEvent)
		NativeWindow.setOnKeyUpListener(::handleKeyEvent)
	}

	private fun initTimers()
	{
		renderTimer = NativeWindow.setInterval(renderInterval, this::draw)
		physicsTimer = NativeWindow.setInterval(physicsInterval, this::performPhysics)
	}

	private fun createRenderManager(): ArkadeRenderManager<*>
	{
		val size = Vector(canvas.width.toDouble(), canvas.height.toDouble())
		return ArkadeRenderManager(elementProvider.render(size), container, createRenderScheduler())
	}

	fun stop()
	{
		resetEventListeners()
		resetTimers()
	}

	private fun resetEventListeners()
	{
		canvas.setOnMouseDownListener(null)
		canvas.setOnMouseMoveListener(null)
		canvas.setOnMouseUpListener(null)
		canvas.setOnMouseEnterListener(null)
		canvas.setOnMouseLeaveListener(null)
		NativeWindow.setOnKeyDownListener(null)
		NativeWindow.setOnKeyPressListener(null)
		NativeWindow.setOnKeyUpListener(null)
	}

	private fun resetTimers()
	{
		renderTimer?.let { NativeWindow.clearInterval(it) }
		physicsTimer?.let { NativeWindow.clearInterval(it) }
	}

	private fun draw()
	{
		canvasContext.clearViewport()
		container.draw(canvasContext)
	}

	private fun performPhysics() = container.performPhysics(createPhysicsContext())

	private fun createPhysicsContext() = PhysicsContext(calculateDeltaTime())

	private fun calculateDeltaTime(): Duration
	{
		val currentTime = NativeWindow.now()
		val lastTime = lastPhysicsTime ?: currentTime
		lastPhysicsTime = currentTime
		return (currentTime - lastTime).milliseconds
	}

	private fun handleMouseEvent(event: NativeMouseEvent) = container.handleEvent(InputEvent.Mouse.from(event))

	private fun handleKeyEvent(event: NativeKeyboardEvent) = container.handleEvent(InputEvent.Key.from(event))
}

fun startOnCanvas(canvasId: String,
                  renderInterval: Int,
                  physicsInterval: Int,
                  elementProvider: ArkadeRenderScope.(size: Vector) -> ArkadeElement<*>) =
		UraniumLauncher(canvasId, renderInterval, physicsInterval, elementProvider).start()

