package pl.karol202.uranium.webcanvas.values

sealed class Color(val asStyle: String)
{
	companion object
	{
		fun raw(value: String) = RawColor(value)

		fun rgb(red: Int, green: Int, blue: Int) = RGBColor(red, green, blue)

		fun rgba(red: Int, green: Int, blue: Int, alpha: Int) = RGBAColor(red, green, blue, alpha)
	}

	data class RawColor(val name: String) : Color(name)

	data class RGBColor(val red: Int,
	                    val green: Int,
	                    val blue: Int) : Color("rgb($red,$green,$blue)")

	data class RGBAColor(val red: Int,
	                     val green: Int,
	                     val blue: Int,
	                     val alpha: Int) : Color("rgb($red,$green,$blue,$alpha)")
}
