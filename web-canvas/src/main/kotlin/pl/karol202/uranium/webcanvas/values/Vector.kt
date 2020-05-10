package pl.karol202.uranium.webcanvas.values

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

private const val EPSILON = 0.0001

data class Vector(val x: Double = 0.0,
                  val y: Double = 0.0)
{
	companion object
	{
		val ZERO = Vector()
	}

	val normalized get() = (this / length) ?: ZERO
	val length get() = sqrt(x.pow(2) + y.pow(2))

	override fun equals(other: Any?) = other is Vector && abs(other.x - x) <= EPSILON && abs(other.y - y) <= EPSILON

	override fun hashCode() = 31 * x.hashCode() + y.hashCode()

	operator fun unaryMinus() = Vector(-x, -y)

	operator fun plus(other: Vector) = Vector(x + other.x, y + other.y)

	operator fun minus(other: Vector) = Vector(x - other.x, y - other.y)

	operator fun times(factor: Double) = Vector(x * factor, y * factor)

	operator fun div(factor: Double) = if(factor != 0.0) Vector(x / factor, y / factor) else null

	infix fun clampIn(bounds: Bounds) =
			Vector(x.coerceIn(bounds.start.x, bounds.end.x), y.coerceIn(bounds.start.y, bounds.end.y))
}

fun Iterable<Vector>.average() = Vector(map { it.x }.average(), map { it.y }.average())
