package pl.karol202.uranium.arkade.canvas.values

import pl.karol202.uranium.arkade.canvas.dom.values.NativeFillStyle
import pl.karol202.uranium.arkade.canvas.draw.DrawContext

abstract class FillStyle
{
	internal abstract fun createNativeStyle(context: DrawContext): NativeFillStyle
}
