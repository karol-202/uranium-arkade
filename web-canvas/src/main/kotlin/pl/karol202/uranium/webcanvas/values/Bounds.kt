package pl.karol202.uranium.webcanvas.values

import kotlin.math.abs
import kotlin.math.min

data class Bounds(val start: Vector = Vector.ZERO,
                  val size: Vector = Vector.ZERO)
{
	val halfSize by lazy { (size / 2.0)!! }
	val center by lazy { start + halfSize }
	val end by lazy { start + size }

	val x by lazy { start.x }
	val y by lazy { start.y }
	val width by lazy { size.x }
	val height by lazy { size.y }

	constructor(x: Double, y: Double, width: Double, height: Double) : this(Vector(x, y), Vector(width, height))

	operator fun contains(vector: Vector) =
			vector.x >= start.x && vector.y >= start.y && vector.x <= end.x && vector.y <= end.y

	infix fun intersectsWith(other: Bounds) =
			other.start.x <= end.x && other.start.y <= end.y && other.end.x >= start.x && other.end.y >= start.y

	operator fun plus(vector: Vector) = Bounds(start + vector, size)

	operator fun minus(vector: Vector) = Bounds(start - vector, size)

	operator fun times(vector: Vector) = Bounds(start * vector, size * vector).fixNegativeSize()

	operator fun times(factor: Double) = Bounds(start * factor, size * factor).fixNegativeSize()

	operator fun div(vector: Vector) =
			if(vector.x != 0.0 && vector.y != 0.0) Bounds((start / vector)!!, (size / vector)!!) else null

	operator fun div(factor: Double) =
			if(factor != 0.0) Bounds((start / factor)!!, (size / factor)!!) else null

	private fun fixNegativeSize() = Bounds(start = Vector(min(start.x, end.x), min(start.y, end.y)),
	                                       size = Vector(abs(size.x), abs(size.y)))
}
