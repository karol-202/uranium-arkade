package pl.karol202.uranium.webcanvas.values

data class Path(val points: List<Vector>,
                val closed: Boolean)
{
	companion object
	{
		fun closed(vararg points: Vector) = Path(points.toList(), closed = true)

		fun open(vararg points: Vector) = Path(points.toList(), closed = false)
	}
}
