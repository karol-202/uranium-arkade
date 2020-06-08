package pl.karol202.uranium.arkade.htmlcanvas.values

import pl.karol202.uranium.arkade.htmlcanvas.dom.values.NativeFillStyle
import pl.karol202.uranium.arkade.htmlcanvas.draw.DrawContext

abstract class FillStyle
{
	internal abstract fun createNativeStyle(context: DrawContext): NativeFillStyle
}
