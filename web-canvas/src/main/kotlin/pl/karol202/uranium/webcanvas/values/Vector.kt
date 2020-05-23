package pl.karol202.uranium.webcanvas.values

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random

private const val EPSILON = 0.0001

data class Vector(val x: Double = 0.0,
                  val y: Double = 0.0)
{
	companion object
	{
		val ZERO = Vector()

		fun randomFraction() = Vector(Random.nextDouble(), Random.nextDouble())

		fun randomDirection() = PolarVector.randomDirection().asCartesian
	}

	val normalized by lazy { (this / length) ?: ZERO }
	val length by lazy { sqrt(x.pow(2) + y.pow(2)) }

	override fun equals(other: Any?) = other is Vector && abs(other.x - x) <= EPSILON && abs(other.y - y) <= EPSILON

	override fun hashCode() = 31 * x.hashCode() + y.hashCode()

	operator fun unaryMinus() = Vector(-x, -y)

	operator fun plus(other: Vector) = Vector(x + other.x, y + other.y)

	operator fun plus(factor: Double) = Vector(x + factor, y + factor)

	operator fun minus(other: Vector) = Vector(x - other.x, y - other.y)

	operator fun minus(factor: Double) = Vector(x - factor, y - factor)

	operator fun times(other: Vector) = Vector(x * other.x, y * other.y)

	operator fun times(factor: Double) = Vector(x * factor, y * factor)

	operator fun div(other: Vector) = if(other.x != 0.0 && other.y != 0.0) Vector(x / other.x, y / other.y) else null

	operator fun div(factor: Double) = if(factor != 0.0) Vector(x / factor, y / factor) else null

	infix fun dot(other: Vector) = (x * other.x) + (y * other.y)

	infix fun clampIn(bounds: Bounds) =
			Vector(x.coerceIn(bounds.start.x, bounds.end.x), y.coerceIn(bounds.start.y, bounds.end.y))
}

operator fun Double.div(vector: Vector) =
		if(vector.x != 0.0 && vector.y != 0.0) Vector(this / vector.x, this / vector.y) else null

fun Iterable<Vector>.average() = Vector(map { it.x }.average(), map { it.y }.average())
