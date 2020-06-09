package pl.karol202.uranium.arkade.htmlcanvas.dom.values

internal expect class NativeFillStyle
{
	companion object
	{
		fun fromString(string: String): NativeFillStyle
	}
}
