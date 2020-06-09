package pl.karol202.uranium.arkade.htmlcanvas.values

import pl.karol202.uranium.arkade.htmlcanvas.dom.values.NativePath

data class Path internal constructor(internal val native: NativePath)
{
	companion object
	{
		fun fromSVG(data: String) = Path(NativePath.fromData(data))

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

		private fun createNativePath(vararg points: Vector) = NativePath.create().apply {
			moveTo(points.first().x, points.first().y)
			points.drop(1).forEach {
				lineTo(it.x, it.y)
			}
		}
	}
}
