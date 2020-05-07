package pl.karol202.uranium.webcanvas.physics.force

import pl.karol202.uranium.webcanvas.physics.PhysicsBody
import pl.karol202.uranium.webcanvas.values.Vector

data class HomogenousForce(private val force: Vector) : Force
{
	override fun getForceAt(body: PhysicsBody) = force

	override fun translate(vector: Vector) = this
}
