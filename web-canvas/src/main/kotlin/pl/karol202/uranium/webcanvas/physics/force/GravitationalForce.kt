package pl.karol202.uranium.webcanvas.physics.force

import pl.karol202.uranium.webcanvas.physics.PhysicsBody
import pl.karol202.uranium.webcanvas.values.Vector

data class GravitationalForce(private val force: Force) : Force
{
	companion object
	{
		fun homogenous(vector: Vector) = GravitationalForce(HomogenousForce(vector))
	}

	override fun getForceAt(body: PhysicsBody) = force.getForceAt(body) * body.mass

	override fun translate(vector: Vector) = copy(force = force.translate(vector))
}
