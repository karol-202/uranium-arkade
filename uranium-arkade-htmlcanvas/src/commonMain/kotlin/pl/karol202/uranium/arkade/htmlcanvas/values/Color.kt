package pl.karol202.uranium.arkade.htmlcanvas.values

import pl.karol202.uranium.arkade.htmlcanvas.dom.values.NativeFillStyle
import pl.karol202.uranium.arkade.htmlcanvas.draw.DrawContext

sealed class Color(val asText: String) : FillStyle()
{
	companion object
	{
		fun raw(value: String) = RawColor(value)

		fun rgb(red: Int, green: Int, blue: Int) = RGBColor(red, green, blue)

		fun rgba(red: Int, green: Int, blue: Int, alpha: Double) = RGBAColor(red, green, blue, alpha)

		fun hsl(hue: Int, saturation: Double, lightness: Double) = HSLColor(hue, saturation, lightness)

		fun hsla(hue: Int, saturation: Double, lightness: Double, alpha: Double) = HSLAColor(hue, saturation, lightness, alpha)
	}

	data class RawColor(val name: String) : Color(name)

	data class RGBColor(val red: Int,
	                    val green: Int,
	                    val blue: Int) : Color("rgb($red,$green,$blue)")

	data class RGBAColor(val red: Int,
	                     val green: Int,
	                     val blue: Int,
	                     val alpha: Double) : Color("rgba($red,$green,$blue,$alpha)")

	data class HSLColor(val hue: Int,
	                    val saturation: Double,
	                    val lightness: Double) : Color("hsl($hue,${saturation * 100}%,${lightness * 100}%)")

	data class HSLAColor(val hue: Int,
	                     val saturation: Double,
	                     val lightness: Double,
	                     val alpha: Double) : Color("hsla($hue,${saturation * 100}%,${lightness * 100}%,$alpha)")

	override fun createNativeStyle(context: DrawContext) = NativeFillStyle.fromString(asText)
}
