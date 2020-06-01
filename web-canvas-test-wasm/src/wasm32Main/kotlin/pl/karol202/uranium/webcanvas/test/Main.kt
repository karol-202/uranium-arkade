package pl.karol202.uranium.webcanvas.test

import pl.karol202.uranium.webcanvas.component.primitives.viewportFill
import pl.karol202.uranium.webcanvas.startOnCanvas
import pl.karol202.uranium.webcanvas.values.Color

fun main()
{
	startOnCanvas("canvas", 20, 20) { viewportFill(fillStyle = Color.raw("blue")) }
}
