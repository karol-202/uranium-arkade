package pl.karol202.uranium.core.common

import pl.karol202.uranium.core.context.UContext
import pl.karol202.uranium.core.element.UElement

abstract class UProps(val key: Any)

abstract class ChildrenProps<C : UContext<*>>(key: Any,
                                              val children: List<UElement<C, *>> = emptyList()) : UProps(key)
