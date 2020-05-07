package pl.karol202.uranium.webcanvas.physics.force

import pl.karol202.uranium.webcanvas.physics.PhysicsBody
import pl.karol202.uranium.webcanvas.values.Vector
import kotlin.math.pow

data class RadialForce(private val center: Vector,
                       private val intensity: Double,
                       private val linearAttenuation: Double,
                       private val quadraticAttenuation: Double) : Force
{
	override fun getForceAt(body: PhysicsBody): Vector
	{
		val offset = center - body.position

		val distance = offset.length
		val attenuation = 1 + (linearAttenuation * distance) + (quadraticAttenuation * distance.pow(2))
		val value = intensity / attenuation

		val direction = offset.normalized
		return direction * value
	}

	override fun translate(vector: Vector) = copy(center = center - vector)
}
