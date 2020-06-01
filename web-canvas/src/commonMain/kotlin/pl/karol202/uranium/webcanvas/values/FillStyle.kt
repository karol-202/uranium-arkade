package pl.karol202.uranium.webcanvas.values

import pl.karol202.uranium.webcanvas.dom.values.NativeFillStyle
import pl.karol202.uranium.webcanvas.draw.DrawContext

abstract class FillStyle
{
	internal abstract fun createNativeStyle(context: DrawContext): NativeFillStyle
}
