package pl.karol202.uranium.core.native

open class NativeNode<N>(open val native: Native<N>)
{
	class Container<N>(override val native: NativeContainer<N>,
	                   val children: List<NativeNode<N>>) : NativeNode<N>(native)
}
