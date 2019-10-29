package pl.karol202.uranium.core.tree

import pl.karol202.uranium.core.element.UElement

// TODO Implement this method in correct way (not removing everything every time)
fun <N> dispatchDiff(currentNodes: List<TreeNode<N, *>>,
                     newElements: List<UElement<N, *>>): List<TreeNodeOperation<N>>
{
	val operations = mutableListOf<TreeNodeOperation<N>>()
	currentNodes.map { TreeNodeOperation.DestroyAndDetachNode(it) }.forEach { operations.add(it) }
	newElements.mapIndexed { index, element -> TreeNodeOperation.CreateAndAttachNode(element, index) }.forEach { operations.add(it) }
	return operations
}
