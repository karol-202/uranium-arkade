package pl.karol202.uranium.arkade.htmlcanvas.dom.values

import org.w3c.dom.Path2D

actual class NativePath(val path2D: Path2D)
{
	actual companion object
	{
		actual fun create() = NativePath(Path2D())

		actual fun fromData(data: String) = NativePath(Path2D(data))
	}

	actual fun moveTo(x: Double, y: Double) = path2D.moveTo(x, y)

	actual fun lineTo(x: Double, y: Double) = path2D.lineTo(x, y)

	actual fun closePath() = path2D.closePath()
}
