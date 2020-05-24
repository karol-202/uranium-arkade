package pl.karol202.uranium.webcanvas.draw

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import pl.karol202.uranium.webcanvas.values.Vector

val HTMLCanvasElement.context2d get() = getContext("2d") as CanvasRenderingContext2D
val HTMLCanvasElement.size get() = Vector(width.toDouble(), height.toDouble())

fun HTMLCanvasElement.fixBounds()
{
	width = clientWidth
	height = clientHeight
}
