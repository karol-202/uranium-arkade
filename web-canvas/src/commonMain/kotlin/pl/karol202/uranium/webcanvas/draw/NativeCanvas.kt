package pl.karol202.uranium.webcanvas.draw

expect class NativeCanvas
{
	val context: NativeCanvasContext

	var width: Int
	var height: Int
	val clientWidth: Int
	val clientHeight: Int
}

internal expect fun getNativeCanvas(canvasId: String): NativeCanvas
