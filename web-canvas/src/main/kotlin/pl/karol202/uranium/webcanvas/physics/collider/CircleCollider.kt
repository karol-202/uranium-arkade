package pl.karol202.uranium.webcanvas.physics.collider

import pl.karol202.uranium.webcanvas.values.Bounds
import pl.karol202.uranium.webcanvas.values.Vector

data class CircleCollider(val center: Vector,
                          val radius: Double) : Collider
{
	override val boundingBox = Bounds(center.x - radius, center.y - radius, 2 * radius, 2 * radius)

	override fun translate(vector: Vector) = copy(center = center + vector)

	override fun accept(collider: Collider) = collider.visit(this)

	override fun visit(collider: RectCollider) = detectCollision(collider, this)

	override fun visit(collider: CircleCollider) = detectCollision(collider, this)
}
