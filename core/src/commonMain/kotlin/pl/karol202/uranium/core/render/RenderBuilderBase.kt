package pl.karol202.uranium.core.render

import pl.karol202.uranium.core.element.UElement

@RenderDsl
open class RenderBuilderBase<N>
{
	var elements = emptyList<UElement<N, *>>()
		private set

	val size get() = elements.size

	operator fun UElement<N, *>.unaryPlus() = also {
		this@RenderBuilderBase.elements += it
	}

	operator fun List<UElement<N, *>>.unaryPlus() = also {
		this@RenderBuilderBase.elements += it
	}
}
