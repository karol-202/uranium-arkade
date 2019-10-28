package pl.karol202.uranium.core.native

interface Native<N>

interface NativeContainer<N> : Native<N>
{
	val children: List<Native<N>>

	fun attach(native: Native<N>, index: Int)

	fun detach(native: Native<N>)
}

fun <N> Native<N>.asNativeNode(): NativeNode<N> = (this as? NativeContainer<N>)?.asNativeNode() ?: NativeNode(this)

fun <N> NativeContainer<N>.asNativeNode() = NativeNode.Container(this, children.map { it.asNativeNode() })
