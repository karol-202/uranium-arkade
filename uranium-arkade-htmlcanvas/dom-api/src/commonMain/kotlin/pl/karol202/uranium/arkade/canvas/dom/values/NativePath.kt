package pl.karol202.uranium.arkade.canvas.dom.values

expect class NativePath
{
	companion object
	{
		fun create(): NativePath

		fun fromData(data: String): NativePath
	}

	fun moveTo(x: Double, y: Double)

	fun lineTo(x: Double, y: Double)

	fun closePath()
}
