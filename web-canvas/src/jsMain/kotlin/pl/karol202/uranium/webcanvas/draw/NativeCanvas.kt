package pl.karol202.uranium.webcanvas.draw

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import kotlin.browser.document

actual class NativeCanvas(private val canvas: HTMLCanvasElement)
{
	actual val context get() = NativeCanvasContext(canvas.getContext("2d") as CanvasRenderingContext2D)

	actual var width = canvas.width
	actual var height = canvas.height
	actual val clientWidth = canvas.clientWidth
	actual val clientHeight = canvas.clientHeight
}

internal actual fun getNativeCanvas(canvasId: String) =
		NativeCanvas(document.getElementById(canvasId) as HTMLCanvasElement)
