package pl.karol202.uranium.webcanvas.dom.canvas

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import pl.karol202.uranium.webcanvas.dom.input.NativeMouseEvent
import kotlin.browser.document

actual class NativeCanvas(private val canvas: HTMLCanvasElement)
{
	actual val context get() = NativeCanvasContext(canvas.getContext("2d") as CanvasRenderingContext2D)

	actual var width = canvas.width
	actual var height = canvas.height
	actual val clientWidth = canvas.clientWidth
	actual val clientHeight = canvas.clientHeight

	actual fun setOnMouseDownListener(listener: ((NativeMouseEvent) -> Unit)?)
	{
		canvas.onmousedown = { listener?.invoke(NativeMouseEvent(it)) }
	}

	actual fun setOnMouseMoveListener(listener: ((NativeMouseEvent) -> Unit)?)
	{
		canvas.onmousemove = { listener?.invoke(NativeMouseEvent(it)) }
	}

	actual fun setOnMouseUpListener(listener: ((NativeMouseEvent) -> Unit)?)
	{
		canvas.onmouseup = { listener?.invoke(NativeMouseEvent(it)) }
	}

	actual fun setOnMouseEnterListener(listener: ((NativeMouseEvent) -> Unit)?)
	{
		canvas.onmouseenter = { listener?.invoke(NativeMouseEvent(it)) }
	}

	actual fun setOnMouseLeaveListener(listener: ((NativeMouseEvent) -> Unit)?)
	{
		canvas.onmouseleave = { listener?.invoke(NativeMouseEvent(it)) }
	}
}

actual fun getNativeCanvas(canvasId: String) = NativeCanvas(document.getElementById(canvasId) as HTMLCanvasElement)
