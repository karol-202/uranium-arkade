package pl.karol202.uranium.arkade.canvas.physics.collider

import pl.karol202.uranium.arkade.canvas.physics.Collision
import pl.karol202.uranium.arkade.canvas.values.Direction
import pl.karol202.uranium.arkade.canvas.values.Vector

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

fun detectCollision(collider1: RectCollider, collider2: RectCollider): Collision?
{
	val rect1 = collider1.bounds
	val rect2 = collider2.bounds
	if(!(rect1 intersectsWith rect2)) return null
	val (direction, penetrationDepth) = mapOf(Direction.LEFT to rect1.end.x - rect2.start.x,
	                                          Direction.TOP to rect1.end.y - rect2.start.y,
	                                          Direction.RIGHT to rect2.end.x - rect1.start.x,
	                                          Direction.BOTTOM to rect2.end.y - rect1.start.y).minBy { it.value }!!
	val penetration = direction.vector * penetrationDepth
	return Collision(selfCollider = collider1, otherCollider = collider2,
	                 penetration = penetration,
	                 selfNormal = -direction.vector, otherNormal = direction.vector)
}

fun detectCollision(collider1: CircleCollider, collider2: CircleCollider): Collision?
{
	val circle1 = collider1.circle
	val circle2 = collider2.circle
	val offset = circle1.center - circle2.center
	val offsetNormalized = offset.normalized
	val radiusSum = circle1.radius + circle2.radius
	val penetrationDepth = radiusSum - offset.length
	if(penetrationDepth < 0) return null
	val penetration = offsetNormalized * penetrationDepth
	return Collision(selfCollider = collider1, otherCollider = collider2,
	                 penetration = penetration,
	                 selfNormal = -offsetNormalized,
	                 otherNormal = offsetNormalized)
}

fun detectCollision(collider1: RectCollider, collider2: CircleCollider): Collision?
{
	val rect = collider1.bounds
	val circle = collider2.circle
	val (selfNormalDir, centerToEdgeDist) = mapOf(Direction.LEFT to circle.center.x - rect.start.x,
	                                              Direction.TOP to circle.center.y - rect.start.y,
	                                              Direction.RIGHT to rect.end.x - circle.center.x,
	                                              Direction.BOTTOM to rect.end.y - circle.center.y).minBy { it.value }!!

	val (penetrationDepth, penetrationDir) = if(circle.center !in collider1.bounds)
	{
		val closestPointInRect = Vector(x = circle.center.x.coerceIn(collider1.bounds.start.x, collider1.bounds.end.x),
		                                y = circle.center.y.coerceIn(collider1.bounds.start.y, collider1.bounds.end.y))
		val circleToRect = closestPointInRect - circle.center
		val penetrationDepth = circle.radius - circleToRect.length
		if(penetrationDepth < 0) return null
		val penetrationDir = circleToRect.normalized
		penetrationDepth to penetrationDir
	}
	else
	{
		val penetrationDepth = centerToEdgeDist + circle.radius
		val penetrationDir = -selfNormalDir.vector
		penetrationDepth to penetrationDir
	}

	val penetration = penetrationDir * penetrationDepth
	return Collision(selfCollider = collider1, otherCollider = collider2,
	                 penetration = penetration,
	                 selfNormal = selfNormalDir.vector, otherNormal = penetrationDir)
}
