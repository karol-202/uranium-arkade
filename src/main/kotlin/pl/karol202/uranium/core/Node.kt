package pl.karol202.uranium.core

interface Node<P : Props>
{
	val props: P
}

interface ParentNode
{
	val children: List<Node<*>>
}

class ComponentNode<P : Props>(val componentConstructor: (P) -> Component<P>,
                               override val props: P) : Node<P>
{
	fun createComponent() = componentConstructor(props)
}
