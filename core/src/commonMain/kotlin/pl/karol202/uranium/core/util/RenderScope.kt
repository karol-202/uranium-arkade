package pl.karol202.uranium.core.util

interface RenderScope<N>

@RenderDsl
open class RenderBuilder<N> : RenderBuilderBase<N>(), RenderScope<N>

fun <N> (RenderBuilder<N>.() -> Unit).render() = RenderBuilder<N>().also(this).elements
