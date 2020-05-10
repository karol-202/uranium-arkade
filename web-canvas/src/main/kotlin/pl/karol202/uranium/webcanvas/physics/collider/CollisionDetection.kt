package pl.karol202.uranium.webcanvas.physics.collider

import pl.karol202.uranium.webcanvas.physics.Collision
import pl.karol202.uranium.webcanvas.values.Direction
import pl.karol202.uranium.webcanvas.values.Vector
import kotlin.math.abs
import kotlin.math.sign

fun checkForCollisions(selfCollider: Collider, otherColliders: List<Collider>) =
		otherColliders.filterPossiblyCollidingColliders(selfCollider).detectCollisions(selfCollider)

private fun List<Collider>.filterPossiblyCollidingColliders(selfCollider: Collider) =
		filter { areBoundingBoxesColliding(selfCollider, it) }

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
	val (direction, penetrationDepth) = mapOf(Direction.LEFT to bounds1.end.x - bounds2.start.x,
	                                          Direction.TOP to bounds1.end.y - bounds2.start.y,
	                                          Direction.RIGHT to bounds2.end.x - bounds1.start.x,
	                                          Direction.BOTTOM to bounds2.end.y - bounds1.start.y)
			.minBy { it.value }!!
	val penetration = direction.vector * penetrationDepth
	return Collision(selfCollider = rect1, otherCollider = rect2,
	                 penetration = penetration,
	                 selfNormal = -direction.vector, otherNormal = direction.vector)
}

fun detectCollision(circle1: CircleCollider, circle2: CircleCollider): Collision?
{
	val offset = circle1.center - circle2.center
	val offsetNormalized = offset.normalized
	val radiusSum = circle1.radius + circle2.radius
	val penetrationDepth = radiusSum - offset.length
	if(penetrationDepth < 0) return null
	val penetration = offsetNormalized * penetrationDepth
	return Collision(selfCollider = circle1, otherCollider = circle2,
	                 penetration = penetration,
	                 selfNormal = -offsetNormalized,
	                 otherNormal = offsetNormalized)
}

fun detectCollision(rect: RectCollider, circle: CircleCollider): Collision?
{
	val rectCenter = rect.bounds.center
	val rectCentricBounds = rect.bounds - rectCenter
	val offset = circle.center - rectCenter
	val closestPointInRect = (offset clampIn rectCentricBounds) + rectCenter
	val circleToRect = closestPointInRect - circle.center
	val circleToRectNormalized = circleToRect.normalized
	val penetrationDepth = circle.radius - circleToRect.length
	if(penetrationDepth < 0) return null
	val penetration = circleToRectNormalized * penetrationDepth
	val selfNormal = mapOf(Direction.LEFT to circle.center.x - rect.bounds.start.x,
	                       Direction.TOP to circle.center.y - rect.bounds.start.y,
	                       Direction.RIGHT to rect.bounds.end.x - circle.center.x,
	                       Direction.BOTTOM to rect.bounds.end.y - circle.center.y)
			.minBy { it.value }!!.key.vector
	return Collision(selfCollider = rect, otherCollider = circle,
	                 penetration = penetration,
	                 selfNormal = selfNormal, otherNormal = circleToRectNormalized)
}
