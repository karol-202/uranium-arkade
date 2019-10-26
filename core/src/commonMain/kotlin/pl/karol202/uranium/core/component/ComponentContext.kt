package pl.karol202.uranium.core.tree

interface ComponentContext
{
	fun invalidate()
}

fun componentContext(invalidate: () -> Unit) = object : ComponentContext
{
	override fun invalidate() = invalidate()
}
