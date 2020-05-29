package pl.karol202.uranium.webcanvas.values

import org.w3c.dom.Path2D

data class Path(val nativePath: Path2D)
{
	companion object
	{
		fun fromSVG(data: String) = Path(Path2D(d = data))

		fun closed(vararg points: Vector): Path
		{
			if(points.size < 2) throw IllegalStateException("Not enough points")
			return Path(createNativePath(*points).apply {
				closePath()
			})
		}

		fun open(vararg points: Vector): Path
		{
			if(points.size < 2) throw IllegalStateException("Not enough points")
			return Path(createNativePath(*points))
		}

		private fun createNativePath(vararg points: Vector) = Path2D().apply {
			moveTo(points.first().x, points.first().y)
			points.drop(1).forEach {
				lineTo(it.x, it.y)
			}
		}
	}
}
