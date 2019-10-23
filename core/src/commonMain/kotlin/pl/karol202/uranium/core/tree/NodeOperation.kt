package pl.karol202.uranium.core.tree

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.UElement

sealed class NodeOperation<N>
{
	class CreateNode<N>(val element: UElement<N, *>) : NodeOperation<N>()

	class UpdateNode<N, P : UProps>(val node: TreeNode<N, P>,
	                                val element: UElement<N, P>) : NodeOperation<N>()

	class AttachNode<N>(val node: TreeNode<N, *>,
	                    val index: Int) : NodeOperation<N>()

	class DetachNode<N>(val node: TreeNode<N, *>) : NodeOperation<N>()

	class DestroyNode<N>(val node: TreeNode<N, *>) : NodeOperation<N>()
}
