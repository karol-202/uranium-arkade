package pl.karol202.uranium.webcanvas.physics.collider

import pl.karol202.uranium.webcanvas.values.Circle
import pl.karol202.uranium.webcanvas.values.Vector

data class CircleCollider(val circle: Circle,
                          override val payload: Any? = null) : Collider
{
	override val boundingBox = circle.boundingBox

	override fun translate(vector: Vector) = copy(circle = circle + vector)

	// TODO Make CircleCollider a EllipseCollider and properly handle radius scaling
	override fun scale(vector: Vector) = copy(circle = circle * vector.x)

	override fun accept(collider: Collider) = collider.visit(this)

	override fun visit(collider: RectCollider) = detectCollision(collider, this)

	override fun visit(collider: CircleCollider) = detectCollision(collider, this)
}
