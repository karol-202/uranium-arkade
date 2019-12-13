package pl.karol202.uranium.core.render

import pl.karol202.uranium.core.element.UElement

interface URenderBuilder<N> : URenderScope<N>
{
	operator fun UElement<N, *>.unaryPlus()
}

fun <N> (URenderBuilder<N>.() -> Unit).render() = URenderBuilderImpl<N>().also(this).elements

private class URenderBuilderImpl<N> : URenderBuilder<N>
{
	var elements = emptyList<UElement<N, *>>()
		private set

	override operator fun UElement<N, *>.unaryPlus()
	{
		this@URenderBuilderImpl.elements += this
	}
}
