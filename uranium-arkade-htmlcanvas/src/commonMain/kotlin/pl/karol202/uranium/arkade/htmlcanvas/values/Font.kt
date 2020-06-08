package pl.karol202.uranium.arkade.htmlcanvas.values

data class Font(val style: Style? = null,
                val variant: Variant? = null,
                val weight: Weight? = null,
                val size: Int,
                val families: List<String>)
{
	enum class Style(val native: String)
	{
		NORMAL("normal"),
		ITALIC("italic"),
		OBLIQUE("oblique")
	}

	enum class Variant(val native: String)
	{
		NORMAL("normal"),
		SMALL_CAPS("small-caps")
	}

	enum class Weight(val native: String)
	{
		NORMAL("normal"),
		BOLD("bold"),
		BOLDER("bolder"),
		LIGHTER("lighter"),
		WEIGHT_100("100"),
		WEIGHT_200("200"),
		WEIGHT_300("300"),
		WEIGHT_400("400"),
		WEIGHT_500("500"),
		WEIGHT_600("600"),
		WEIGHT_700("700"),
		WEIGHT_800("800"),
		WEIGHT_900("900"),
	}

	companion object
	{
		fun create(size: Int, vararg family: String) = Font(size = size, families = family.toList())
	}

	val asText by lazy { "$styleAsText$variantAsText$weightAsText$sizeAsText$familiesAsText" }
	private val styleAsText get() = style?.native?.let { "$it " } ?: ""
	private val variantAsText get() = variant?.native?.let { "$it " } ?: ""
	private val weightAsText get() = weight?.native?.let { "$it " } ?: ""
	private val sizeAsText get() = "${size}px"
	private val familiesAsText get() = families.joinToString(separator = ", ") { "\"$it\"" }

	fun italic() = withStyle(Style.ITALIC)

	fun oblique() = withStyle(Style.OBLIQUE)

	fun smallCaps() = withVariant(Variant.SMALL_CAPS)

	fun bold() = withWeight(Weight.BOLD)

	fun bolder() = withWeight(Weight.BOLDER)

	fun lighter() = withWeight(Weight.LIGHTER)

	fun withStyle(style: Style?) = copy(style = style)

	fun withVariant(variant: Variant?) = copy(variant = variant)

	fun withWeight(weight: Weight?) = copy(weight = weight)
}
