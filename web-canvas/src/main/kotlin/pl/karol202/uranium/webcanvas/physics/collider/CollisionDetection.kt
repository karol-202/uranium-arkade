package pl.karol202.uranium.webcanvas.physics.collider

import pl.karol202.uranium.webcanvas.physics.Collision
import pl.karol202.uranium.webcanvas.values.Direction
import pl.karol202.uranium.webcanvas.values.Vector

fun checkForCollisions(selfCollider: Collider, otherColliders: List<Collider>) =
		otherColliders.filterPossiblyCollidingColliders(selfCollider).detectCollisions(selfCollider)

private fun List<Collider>.filterPossiblyCollidingColliders(selfCollider: Collider) =
		filter { it != selfCollider && areBoundingBoxesColliding(selfCollider, it) }

private fun areBoundingBoxesColliding(collider1: Collider, collider2: Collider) =
		collider1.boundingBox intersectsWith collider2.boundingBox

private fun List<Collider>.detectCollisions(selfCollider: Collider) =
		mapNotNull { detectCollision(selfCollider, it) }

private fun detectCollision(selfCollider: Collider, other: Collider) =
		selfCollider.accept(other)?.swapCollidersIfNecessary(selfCollider)

private fun Collision.swapCollidersIfNecessary(selfCollider: Collider) =
		if(selfCollider == this.otherCollider) swapColliders() else this

fun detectCollision(rect1: RectCollider, rect2: RectCollider): Collision?
{
	val bounds1 = rect1.bounds
	val bounds2 = rect2.bounds
	if(bounds1 intersectsWith bounds2) return null
	val penetration = mapOf(Direction.LEFT to bounds1.end.x - bounds2.start.x,
	                        Direction.TOP to bounds1.end.y - bounds2.start.y,
	                        Direction.RIGHT to bounds2.end.x - bounds1.start.x,
	                        Direction.BOTTOM to bounds2.end.y - bounds1.start.y)
			.minBy { it.value }!!.let { it.key.vector * it.value }
	return Collision(rect1, rect2, penetration)
}

fun detectCollision(circle1: CircleCollider, circle2: CircleCollider): Collision?
{
	val offset = circle1.center - circle2.center
	val radiusSum = circle1.radius + circle2.radius
	val penetrationDepth = radiusSum - offset.length
	if(penetrationDepth < 0) return null
	val penetration = offset.normalized * penetrationDepth
	return Collision(circle1, circle2, penetration)
}

fun detectCollision(rect: RectCollider, circle: CircleCollider): Collision?
{
	val rectCenter = rect.bounds.center
	val rectCentricBounds = rect.bounds - rectCenter
	val offset = circle.center - rectCenter
	val closestPointInRect = (offset clampIn rectCentricBounds) + rectCenter
	val circleToRect = closestPointInRect - circle.center
	val penetrationDepth = circle.radius - circleToRect.length
	if(penetrationDepth < 0) return null
	val penetration = circleToRect.normalized * penetrationDepth
	return Collision(rect, circle, penetration)
}
