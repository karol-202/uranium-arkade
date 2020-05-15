package pl.karol202.uranium.webcanvas.physics.collider

import pl.karol202.uranium.webcanvas.physics.Collision
import pl.karol202.uranium.webcanvas.values.Bounds
import pl.karol202.uranium.webcanvas.values.Vector

interface Collider
{
	val boundingBox: Bounds

	fun translate(vector: Vector): Collider

	fun scale(vector: Vector): Collider

	fun accept(collider: Collider): Collision?

	fun visit(collider: RectCollider): Collision?

	fun visit(collider: CircleCollider): Collision?
}
