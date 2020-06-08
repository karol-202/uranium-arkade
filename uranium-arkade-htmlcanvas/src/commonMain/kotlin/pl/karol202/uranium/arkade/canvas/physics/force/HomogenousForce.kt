package pl.karol202.uranium.arkade.canvas.physics.force

import pl.karol202.uranium.arkade.canvas.physics.PhysicsBody
import pl.karol202.uranium.arkade.canvas.values.Vector

data class HomogenousForce(private val force: Vector) : Force
{
	override fun getForceAt(body: PhysicsBody) = force

	override fun translate(vector: Vector) = this

	override fun scale(vector: Vector) = this
}
