package pl.karol202.uranium.core.native

import pl.karol202.uranium.core.util.elementInserted
import pl.karol202.uranium.core.util.elementRemoved
import pl.karol202.uranium.core.util.elementReplaced

class NativeNode<N>(private val native: Native<N>,
                    private val children: List<NativeNode<N>> = emptyList())
{
	private val nativeAsContainer get() = native as NativeContainer

	fun transformedTo(targetChildren: List<NativeNode<N>>) =
			NativeNode(native, children.childrenTransformedTo(targetChildren).detachExcessiveNodes(targetChildren.size))

	private fun List<NativeNode<N>>.childrenTransformedTo(targetChildren: List<NativeNode<N>>) =
			targetChildren.foldIndexed(this) { targetIndex, currentChildren, targetNode ->
				currentChildren.childTransformedTo(targetNode, targetIndex)
			}

	private fun List<NativeNode<N>>.childTransformedTo(targetNode: NativeNode<N>, targetIndex: Int): List<NativeNode<N>>
	{
		val old = withIndex().find { it.value.native == targetNode.native }
		val transformedNode = (old?.value ?: NativeNode(targetNode.native)).transformedTo(targetNode.children)
		return when
		{
			old == null -> nodeAttached(transformedNode, targetIndex)
			old.index > targetIndex -> nodeReattached(old.value, transformedNode, targetIndex)
			old.index < targetIndex -> throw IllegalStateException("Diffing algorithm bug!")
			else -> elementReplaced(old.value, transformedNode)
		}
	}

	private fun List<NativeNode<N>>.detachExcessiveNodes(limit: Int) =
			mapIndexedNotNull { index, node ->
				if(index < limit) node
				else null.also { detach(node) }
			}

	private fun List<NativeNode<N>>.nodeAttached(node: NativeNode<N>, targetIndex: Int) =
			elementInserted(node, targetIndex).also { attach(node, targetIndex) }

	private fun List<NativeNode<N>>.nodeDetached(node: NativeNode<N>) =
			elementRemoved(node).also { detach(node) }

	private fun List<NativeNode<N>>.nodeReattached(oldNode: NativeNode<N>, targetNode: NativeNode<N>, targetIndex: Int) =
			nodeDetached(oldNode).nodeAttached(targetNode, targetIndex)

	private fun attach(node: NativeNode<N>, targetIndex: Int) = nativeAsContainer.attach(node.native, targetIndex)

	private fun detach(node: NativeNode<N>) = nativeAsContainer.detach(node.native)
}
