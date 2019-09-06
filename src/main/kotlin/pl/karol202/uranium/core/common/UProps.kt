package pl.karol202.uranium.core.common

import pl.karol202.uranium.core.context.Context
import pl.karol202.uranium.core.element.UElement

abstract class UProps(val key: Any)

abstract class ChildrenProps<C : Context<*>>(key: Any,
                                             val children: List<UElement<C, *>> = emptyList()) : UProps(key)
