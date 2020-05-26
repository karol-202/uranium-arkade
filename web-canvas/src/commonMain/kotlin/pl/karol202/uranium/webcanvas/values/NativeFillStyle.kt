package pl.karol202.uranium.webcanvas.values

expect class NativeFillStyle
{
	companion object
	{
		internal fun fromString(string: String): NativeFillStyle
	}
}
