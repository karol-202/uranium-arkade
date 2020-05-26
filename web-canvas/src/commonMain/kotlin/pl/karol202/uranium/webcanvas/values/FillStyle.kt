package pl.karol202.uranium.webcanvas.values

import pl.karol202.uranium.webcanvas.draw.DrawContext

interface FillStyle
{
	fun createNativeStyle(context: DrawContext): NativeFillStyle
}
