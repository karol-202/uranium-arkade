package pl.karol202.uranium.webcanvas.values

data class Vector(val x: Double = 0.0,
                  val y: Double = 0.0)
{
	companion object
	{
		val ZERO = Vector()
	}

	operator fun plus(other: Vector) = Vector(x + other.x, y + other.y)

	operator fun minus(other: Vector) = Vector(x - other.x, y - other.y)

	operator fun times(factor: Double) = Vector(x * factor, y * factor)
}
