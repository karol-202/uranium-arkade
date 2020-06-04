package pl.karol202.uranium.arkade.canvas.values

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

data class PolarVector(val angle: Double /* Radians */,
                       val length: Double)
{
	companion object
	{
		fun randomDirection(startAngle: Double = 0.0, endAngle: Double = 2 * PI) =
				PolarVector(angle = Random.nextDouble(startAngle, endAngle),
				            length = 1.0)
	}

	val x get() = length * cos(angle)
	val y get() = length * sin(angle)
	val asCartesian get() = Vector(x, y)
}
