package pl.karol202.uranium.webcanvas.values

actual class NativeFillStyle(val fillStyle: Any)
{
	actual companion object
	{
		internal actual fun fromString(string: String) = NativeFillStyle(string)
	}
}
