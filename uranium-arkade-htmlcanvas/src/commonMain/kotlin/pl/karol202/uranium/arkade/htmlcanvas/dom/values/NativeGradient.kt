package pl.karol202.uranium.arkade.htmlcanvas.dom.values

internal expect class NativeGradient
{
	val asNativeFillStyle: NativeFillStyle

	fun addColorStop(offset: Double, color: String)
}
