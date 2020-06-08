package pl.karol202.uranium.arkade.htmlcanvas.physics.collider

import pl.karol202.uranium.arkade.htmlcanvas.physics.Collision
import pl.karol202.uranium.arkade.htmlcanvas.values.Bounds
import pl.karol202.uranium.arkade.htmlcanvas.values.Vector

interface Collider
{
	val boundingBox: Bounds
	val payload: Any?

	fun translate(vector: Vector): Collider

	fun scale(vector: Vector): Collider

	fun accept(collider: Collider): Collision?

	fun visit(collider: RectCollider): Collision?

	fun visit(collider: CircleCollider): Collision?
}
