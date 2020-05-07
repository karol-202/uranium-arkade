package pl.karol202.uranium.webcanvas.physics.collider

import pl.karol202.uranium.webcanvas.values.Bounds
import pl.karol202.uranium.webcanvas.values.Vector

data class RectCollider(val bounds: Bounds) : Collider
{
	override val boundingBox = bounds

	override fun translate(vector: Vector) = copy(bounds = bounds + vector)
}
