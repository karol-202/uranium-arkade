package pl.karol202.uranium.webcanvas.values

data class Path(val points: List<Vector>,
                val close: Boolean)
{
	companion object
	{
		fun closed(vararg points: Vector) = Path(points.toList(), close = true)

		fun open(vararg points: Vector) = Path(points.toList(), close = false)
	}
}
