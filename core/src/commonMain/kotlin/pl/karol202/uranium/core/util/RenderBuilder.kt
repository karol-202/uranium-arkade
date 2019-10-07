package pl.karol202.uranium.core.util

import pl.karol202.uranium.core.element.UElement

interface RenderScope<N>

@RenderDsl
class RenderBuilder<N> : RenderScope<N>
{
	var elements = emptyList<UElement<N, *>>()
		private set

	val size get() = elements.size

	operator fun UElement<N, *>.unaryPlus() = also {
		this@RenderBuilder.elements += it
	}

	operator fun List<UElement<N, *>>.unaryPlus() = also {
		this@RenderBuilder.elements += it
	}
}

fun <N> (RenderBuilder<N>.() -> Unit).render() = RenderBuilder<N>().also(this).elements
