package pl.karol202.uranium.webcanvas.dom.values

expect class NativeGradient
{
	val asNativeFillStyle: NativeFillStyle

	fun addColorStop(offset: Double, color: String)
}
