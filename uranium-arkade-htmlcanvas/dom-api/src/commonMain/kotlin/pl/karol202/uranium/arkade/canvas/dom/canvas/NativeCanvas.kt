package pl.karol202.uranium.arkade.canvas.dom.canvas

import pl.karol202.uranium.arkade.canvas.dom.input.NativeMouseEvent

expect class NativeCanvas
{
	val context: NativeCanvasContext

	var width: Int
	var height: Int
	val clientWidth: Int
	val clientHeight: Int

	fun setOnMouseDownListener(listener: ((NativeMouseEvent) -> Unit)?)

	fun setOnMouseMoveListener(listener: ((NativeMouseEvent) -> Unit)?)

	fun setOnMouseUpListener(listener: ((NativeMouseEvent) -> Unit)?)

	fun setOnMouseEnterListener(listener: ((NativeMouseEvent) -> Unit)?)

	fun setOnMouseLeaveListener(listener: ((NativeMouseEvent) -> Unit)?)
}

expect fun getNativeCanvas(canvasId: String): NativeCanvas
