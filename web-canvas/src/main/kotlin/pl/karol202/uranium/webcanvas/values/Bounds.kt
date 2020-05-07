package pl.karol202.uranium.webcanvas.values

data class Bounds(val start: Vector = Vector.ZERO,
                  val size: Vector = Vector.ZERO)
{
	val end = start + size

	val x get() = start.x
	val y get() = start.y
	val width get() = size.x
	val height get() = size.x

	constructor(x: Double, y: Double, width: Double, height: Double) : this(Vector(x, y), Vector(width, height))

	operator fun contains(vector: Vector) =
			vector.x >= start.x && vector.y >= start.y && vector.x <= end.x && vector.y <= end.y

	operator fun plus(vector: Vector) = Bounds(start + vector, size)
}
