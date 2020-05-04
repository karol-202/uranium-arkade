package pl.karol202.uranium.webcanvas.values

sealed class Color
{
	companion object
	{
		fun named(name: String) = NamedColor(name)
	}

	data class NamedColor(val name: String) : Color()
	{
		override val asStyle get() = name
	}

	abstract val asStyle: String
}
