package pl.karol202.uranium.core.element

import pl.karol202.uranium.core.context.Context
import pl.karol202.uranium.core.common.HasProps
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.UComponent

class UElement<C : Context<*>, P : UProps>(val componentConstructor: (P) -> UComponent<C, P>,
                                                                            override val props: P) : HasProps<P>
{
	fun createComponent() = componentConstructor(props)
}
