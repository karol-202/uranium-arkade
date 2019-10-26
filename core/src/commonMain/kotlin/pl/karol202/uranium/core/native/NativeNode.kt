package pl.karol202.uranium.core.native

import pl.karol202.uranium.core.util.elementInserted
import pl.karol202.uranium.core.util.elementRemoved

open class NativeNode<N>(open val native: Native<N>)
{
	class Container<N>(override val native: NativeContainer<N>,
	                   val children: List<NativeNode<N>>) : NativeNode<N>(native)
	{
		fun commit(targetChildren: List<NativeNode<N>>) =
				Container(native, targetChildren.foldIndexed(children) { targetIndex, currentChildren, node ->
					commitChild(currentChildren, node, targetIndex)
				})

		private fun commitChild(currentChildren: List<NativeNode<N>>, node: NativeNode<N>, targetIndex: Int) =
				currentChildren.indexOf(node).takeUnless { it == -1 }.let { currentIndex ->
					when
					{
						currentIndex == null -> currentChildren.nodeAttached(node, targetIndex)
						currentIndex > targetIndex -> currentChildren.nodeReattached(node, targetIndex)
						currentIndex < targetIndex -> throw IllegalStateException("Diffing algorithm bug!")
						else -> currentChildren
					}
				}

		private fun List<NativeNode<N>>.nodeAttached(node: NativeNode<N>, targetIndex: Int) =
				elementInserted(node, targetIndex).also {
					native.attach(node.native, targetIndex)
				}

		private fun List<NativeNode<N>>.nodeReattached(node: NativeNode<N>, targetIndex: Int) =
				elementRemoved(node).nodeAttached(node, targetIndex).also {
					native.detach(node.native)
				}
	}
}
