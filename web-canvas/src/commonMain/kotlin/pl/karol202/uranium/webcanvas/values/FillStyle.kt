package pl.karol202.uranium.webcanvas.values

import pl.karol202.uranium.webcanvas.dom.values.NativeFillStyle

interface FillStyle
{
	fun createNativeStyle(context: DrawContext): NativeFillStyle
}
