package pl.karol202.uranium.arkade.canvas.physics.force

import pl.karol202.uranium.arkade.canvas.physics.PhysicsBody
import pl.karol202.uranium.arkade.canvas.values.Vector
import kotlin.math.pow

data class RadialForce(private val center: Vector,
                       private val intensity: Double,
                       private val linearAttenuation: Double,
                       private val quadraticAttenuation: Double,
                       private val scale: Vector = Vector(1.0, 1.0)) : Force
{
	override fun getForceAt(body: PhysicsBody): Vector
	{
		val offset = center - body.position
		val scaledOffset = offset / scale ?: return Vector.ZERO

		val distance = scaledOffset.length
		val attenuation = 1 + (linearAttenuation * distance) + (quadraticAttenuation * distance.pow(2))
		val value = intensity / attenuation

		val direction = scaledOffset.normalized
		return direction * value
	}

	override fun translate(vector: Vector) = copy(center = center + vector)

	override fun scale(vector: Vector) = copy(center = center * vector,
	                                          scale = scale * vector)
}
