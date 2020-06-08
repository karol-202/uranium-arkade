package pl.karol202.uranium.arkade.canvas

import pl.karol202.uranium.arkade.canvas.dom.canvas.getNativeCanvas
import pl.karol202.uranium.arkade.canvas.dom.input.NativeKeyboardEvent
import pl.karol202.uranium.arkade.canvas.dom.input.NativeMouseEvent
import pl.karol202.uranium.arkade.canvas.dom.window.NativeWindow
import pl.karol202.uranium.arkade.canvas.draw.asDrawContext
import pl.karol202.uranium.arkade.canvas.native.nativeContainer
import pl.karol202.uranium.arkade.canvas.physics.PhysicsContext
import pl.karol202.uranium.arkade.canvas.values.InputEvent
import pl.karol202.uranium.core.manager.RenderScheduler
import pl.karol202.uranium.core.render.render
import kotlin.time.Duration
import kotlin.time.milliseconds

expect fun createRenderScheduler(): RenderScheduler

class UraniumLauncher(canvasId: String,
                      private val renderInterval: Int,
                      private val physicsInterval: Int,
                      elementProvider: WCRenderScope.() -> WCElement<*>)
{
	private val canvas = getNativeCanvas(canvasId)
	private val drawContext = canvas.context.asDrawContext
	private val container = nativeContainer()
	private val renderManager by lazy { WCRenderManager(elementProvider.render(), container, createRenderScheduler()) }

	private var renderTimer: Int? = null
	private var physicsTimer: Int? = null

	private var lastPhysicsTime: Double? = null

	fun start()
	{
		initContext()
		initEventListeners()
		renderManager.scheduleInit()
		renderTimer = NativeWindow.setInterval(renderInterval, this::draw)
		physicsTimer = NativeWindow.setInterval(physicsInterval, this::performPhysics)
	}

	private fun initContext()
	{
		drawContext.size = drawContext.clientSize
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

	fun stop()
	{
		renderTimer?.let { NativeWindow.clearInterval(it) }
		physicsTimer?.let { NativeWindow.clearInterval(it) }
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
                  elementProvider: WCRenderScope.() -> WCElement<*>) =
		UraniumLauncher(canvasId, renderInterval, physicsInterval, elementProvider).start()

