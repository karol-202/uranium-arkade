package pl.karol202.uranium.arkade.htmlcanvas.physics.collider

import pl.karol202.uranium.arkade.htmlcanvas.values.Bounds
import pl.karol202.uranium.arkade.htmlcanvas.values.Vector

data class RectCollider(val bounds: Bounds,
                        override val payload: Any? = null) : Collider
{
	override val boundingBox = bounds

	override fun translate(vector: Vector) = copy(bounds = bounds + vector)

	override fun scale(vector: Vector) = copy(bounds = bounds * vector)

	override fun accept(collider: Collider) = collider.visit(this)

	override fun visit(collider: RectCollider) = detectCollision(this, collider)

	override fun visit(collider: CircleCollider) = detectCollision(this, collider)
}
