package pl.karol202.uranium.webcanvas.values

sealed class Color
{
	data class NamedColor(val name: String) : Color()
	{
		override val asStyle get() = name
	}

	abstract val asStyle: String
}
