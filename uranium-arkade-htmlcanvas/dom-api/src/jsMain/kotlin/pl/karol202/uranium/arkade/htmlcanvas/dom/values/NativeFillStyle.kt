package pl.karol202.uranium.arkade.htmlcanvas.dom.values

actual class NativeFillStyle(val fillStyle: Any)
{
	actual companion object
	{
		actual fun fromString(string: String) = NativeFillStyle(string)
	}
}
