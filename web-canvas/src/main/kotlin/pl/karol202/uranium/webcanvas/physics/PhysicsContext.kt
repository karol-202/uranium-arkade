package pl.karol202.uranium.webcanvas.physics

import pl.karol202.uranium.webcanvas.physics.collider.Collider
import pl.karol202.uranium.webcanvas.physics.collider.checkForCollisions
import pl.karol202.uranium.webcanvas.physics.force.Force
import pl.karol202.uranium.webcanvas.values.Vector
import kotlin.time.Duration

data class PhysicsContext(val deltaTime: Duration,
                          val forces: List<Force> = emptyList(),
                          val colliders: List<Collider> = emptyList())
{
	fun getForceAt(body: PhysicsBody) = forces.fold(Vector.ZERO) { current, force -> current + force.getForceAt(body) }

	fun checkForCollisions(collider: Collider) = checkForCollisions(collider, colliders)

	fun withForce(force: Force) = copy(forces = forces + force)

	fun withColliders(colliders: List<Collider>) = copy(colliders = colliders)

	fun translate(vector: Vector) = copy(forces = forces.map { it.translate(vector) },
	                                     colliders = colliders.map { it.translate(vector) })
}
