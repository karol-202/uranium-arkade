package pl.karol202.uranium.webcanvas.values

data class Bounds(val start: Vector = Vector.ZERO,
                  val size: Vector = Vector.ZERO)
{
	val halfSize get() = (size / 2.0)!!
	val center get() = start + halfSize
	val end get() = start + size

	val x get() = start.x
	val y get() = start.y
	val width get() = size.x
	val height get() = size.y

	constructor(x: Double, y: Double, width: Double, height: Double) : this(Vector(x, y), Vector(width, height))

	operator fun contains(vector: Vector) =
			vector.x >= start.x && vector.y >= start.y && vector.x <= end.x && vector.y <= end.y

	infix fun intersectsWith(other: Bounds) =
			other.start.x <= end.x && other.start.y <= end.y && other.end.x >= start.x && other.end.y >= start.y

	operator fun plus(vector: Vector) = Bounds(start + vector, size)

	operator fun minus(vector: Vector) = Bounds(start - vector, size)
}
