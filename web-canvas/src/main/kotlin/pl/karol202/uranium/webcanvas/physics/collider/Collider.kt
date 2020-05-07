package pl.karol202.uranium.webcanvas.physics.collider

import pl.karol202.uranium.webcanvas.values.Bounds
import pl.karol202.uranium.webcanvas.values.Vector

interface Collider
{
	val boundingBox: Bounds

	fun translate(vector: Vector): Collider
}
