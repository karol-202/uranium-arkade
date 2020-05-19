package pl.karol202.uranium.webcanvas.values

data class Circle(val center: Vector = Vector.ZERO,
                  val radius: Double)
{
	val diameter by lazy { 2 * radius }
	val boundingBox by lazy { Bounds(center.x - radius, center.y - radius, diameter, diameter) }

	operator fun plus(offset: Vector) = Circle(center + offset, radius)

	operator fun minus(offset: Vector) = Circle(center - offset, radius)

	// TODO Add ellipse to make multiplying by vector possible

	operator fun times(factor: Double) = Circle(center * factor, radius * factor)

	operator fun div(factor: Double) =
			if(factor != 0.0) Circle(center * factor, radius * factor) else null
}
