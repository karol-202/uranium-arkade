package pl.karol202.uranium.arkade.canvas.dom.values

expect class NativeGradient
{
	val asNativeFillStyle: NativeFillStyle

	fun addColorStop(offset: Double, color: String)
}
