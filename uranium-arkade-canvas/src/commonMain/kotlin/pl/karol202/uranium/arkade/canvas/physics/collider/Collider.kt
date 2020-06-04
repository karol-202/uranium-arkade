package pl.karol202.uranium.arkade.canvas.physics.collider

import pl.karol202.uranium.arkade.canvas.physics.Collision
import pl.karol202.uranium.arkade.canvas.values.Bounds
import pl.karol202.uranium.arkade.canvas.values.Vector

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
