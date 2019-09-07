package pl.karol202.uranium.core.common

import pl.karol202.uranium.core.element.UElement

abstract class UProps(val key: Any)

abstract class ChildrenProps<N>(key: Any,
                                val children: List<UElement<N, *>> = emptyList()) : UProps(key)
