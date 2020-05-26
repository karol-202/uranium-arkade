package pl.karol202.uranium.webcanvas.draw

typealias DrawContext = NativeCanvasContext

typealias DrawOperation = DrawContext.() -> Unit

fun DrawContext.fillViewport() = fillRect(0.0, 0.0, canvas.width.toDouble(), canvas.height.toDouble())

fun DrawContext.clearViewport() = clearRect(0.0, 0.0, canvas.width.toDouble(), canvas.height.toDouble())
