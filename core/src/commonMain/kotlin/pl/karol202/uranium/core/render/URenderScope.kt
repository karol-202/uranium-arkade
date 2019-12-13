package pl.karol202.uranium.core.render

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.UElement

interface URenderScope<N>

internal fun <N> renderScope() = object : URenderScope<N> { }

fun <N> (URenderScope<N>.() -> UElement<N, *>).render() = renderScope<N>().this()

// TODO Investigate why the type inference does not allow to use this method
//  with receiver being a function returning P-covariant element
fun <N, P : UProps> (URenderScope<N>.() -> UElement<N, P>).render() = renderScope<N>().this()
