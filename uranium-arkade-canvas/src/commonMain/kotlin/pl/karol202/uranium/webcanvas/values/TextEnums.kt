package pl.karol202.uranium.webcanvas.values

enum class TextAlign(val native: String)
{
	START("start"),
	LEFT("left"),
	CENTER("center"),
	RIGHT("right"),
	END("end");

	companion object
	{
		fun fromNative(native: String) = values().find { it.native == native }
	}
}

enum class TextBaseline(val native: String)
{
	TOP("top"),
	MIDDLE("middle"),
	BOTTOM("bottom"),
	ALPHABETIC("alphabetic"),
	HANGING("hanging"),
	IDEOGRAPHIC("ideographic");

	companion object
	{
		fun fromNative(native: String) = TextAlign.values().find { it.native == native }
	}
}

enum class TextDirection(val native: String)
{
	LTR("ltr"),
	RTL("rtl"),
	INHERIT("inherit");

	companion object
	{
		fun fromNative(native: String) = TextAlign.values().find { it.native == native }
	}
}
