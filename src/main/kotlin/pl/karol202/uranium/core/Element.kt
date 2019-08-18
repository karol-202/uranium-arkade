package pl.karol202.uranium.core

interface Element<P : Props>
{
	val props: P
}

interface ParentElement
{
	val children: List<Element<*>>
}

class ComponentElement<P : Props>(val componentConstructor: (P) -> Component<P, *>,
                                  override val props: P) : Element<P>
{
	fun createComponent() = componentConstructor(props)
}
