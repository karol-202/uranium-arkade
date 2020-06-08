package pl.karol202.uranium.arkade.htmlcanvas.dom.values

import org.w3c.dom.CanvasGradient

actual class NativeGradient(private val gradient: CanvasGradient)
{
	actual val asNativeFillStyle get() = NativeFillStyle(gradient)

	actual fun addColorStop(offset: Double, color: String) = gradient.addColorStop(offset, color)
}
