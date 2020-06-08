package pl.karol202.uranium.arkade.canvas.physics

import pl.karol202.uranium.arkade.canvas.physics.collider.Collider
import pl.karol202.uranium.arkade.canvas.physics.collider.checkForCollisions
import pl.karol202.uranium.arkade.canvas.physics.force.Force
import pl.karol202.uranium.arkade.canvas.values.Vector
import pl.karol202.uranium.arkade.canvas.values.average
import kotlin.time.Duration

data class PhysicsContext(val deltaTime: Duration,
                          val forces: List<Force> = emptyList(),
                          val colliders: List<Collider> = emptyList())
{
	fun processBody(body: PhysicsBody): PhysicsBody
	{
		val deltaTime = deltaTime.inSeconds
		val force = getForceAt(body)
		val acceleration = force / body.mass ?: return body
		val oldVelocity = body.velocity
		val newVelocity = oldVelocity + (acceleration * deltaTime)
		val offset = listOf(oldVelocity, newVelocity).average() * deltaTime
		return body.copy(position = body.position + offset, velocity = newVelocity)
	}

	fun getForceAt(body: PhysicsBody) = forces.fold(Vector.ZERO) { current, force -> current + force.getForceAt(body) }

	fun checkForCollisions(collider: Collider) = checkForCollisions(collider, colliders)

	fun withForce(force: Force) = copy(forces = forces + force)

	fun withColliders(colliders: List<Collider>) = copy(colliders = colliders)

	fun excludeCollider(collider: Collider) = copy(colliders = colliders - collider)

	fun translate(vector: Vector) = copy(forces = forces.map { it.translate(vector) },
	                                     colliders = colliders.map { it.translate(vector) })

	fun scale(vector: Vector) = copy(forces = forces.map { it.scale(vector) },
	                                 colliders = colliders.map { it.scale(vector) })

	fun noForces() = copy(forces = emptyList())

	fun noColliders() = copy(colliders = emptyList())
}
