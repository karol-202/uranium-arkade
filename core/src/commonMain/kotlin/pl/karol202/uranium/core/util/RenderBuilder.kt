package pl.karol202.uranium.core.util

import pl.karol202.uranium.core.element.UElement

class RenderBuilder<N>
{
	var elements = emptyList<UElement<N, *>>()
		private set

	operator fun UElement<N, *>.unaryPlus() = also {
		this@RenderBuilder.elements += it
	}

	operator fun List<UElement<N, *>>.unaryPlus() = also {
		this@RenderBuilder.elements += it
	}
}

fun <N> (RenderBuilder<N>.() -> Unit).render() = RenderBuilder<N>().also(this).elements
