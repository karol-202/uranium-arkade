package pl.karol202.uranium.webcanvas.test

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement

fun HTMLCanvasElement.fixBounds()
{
	width = clientWidth
	height = clientHeight
}

val HTMLCanvasElement.context2d get() = getContext("2d") as CanvasRenderingContext2D

val CanvasRenderingContext2D.width get() = canvas.width.toDouble()
val CanvasRenderingContext2D.height get() = canvas.height.toDouble()
