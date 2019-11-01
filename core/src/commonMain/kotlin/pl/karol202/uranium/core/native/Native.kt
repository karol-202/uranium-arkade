package pl.karol202.uranium.core.native

interface Native<N>

interface NativeContainer<N> : Native<N>
{
	fun attach(native: Native<N>, index: Int)

	fun detach(native: Native<N>)
}

fun <N> Native<N>.asNode() = NativeNode(this)
