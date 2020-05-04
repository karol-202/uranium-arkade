package pl.karol202.uranium.webcanvas.draw

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement

fun HTMLCanvasElement.fixBounds()
{
	width = clientWidth
	height = clientHeight
}

val HTMLCanvasElement.context2d get() = getContext("2d") as CanvasRenderingContext2D
