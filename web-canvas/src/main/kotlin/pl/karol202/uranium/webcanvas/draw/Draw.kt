package pl.karol202.uranium.webcanvas.draw

import org.w3c.dom.CanvasRenderingContext2D

typealias DrawContext = CanvasRenderingContext2D

typealias DrawOperation = DrawContext.() -> Unit

fun DrawContext.fillCanvas() = fillRect(0.0, 0.0, canvas.width.toDouble(), canvas.height.toDouble())

fun DrawContext.clearCanvas() = clearRect(0.0, 0.0, canvas.width.toDouble(), canvas.height.toDouble())
