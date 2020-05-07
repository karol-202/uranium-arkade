package pl.karol202.uranium.webcanvas.physics

import pl.karol202.uranium.webcanvas.physics.force.Force
import pl.karol202.uranium.webcanvas.values.Vector
import kotlin.time.Duration

data class PhysicsContext(val deltaTime: Duration,
                          val forces: List<Force> = emptyList())
{
	fun withForce(force: Force) = copy(forces = forces + force)

	fun mapForces(transform: (Force) -> Force) = copy(forces = forces.map(transform))

	fun getForceAt(body: PhysicsBody) = forces.fold(Vector.ZERO) { current, force -> current + force.getForceAt(body) }
}
