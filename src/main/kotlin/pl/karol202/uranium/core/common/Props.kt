package pl.karol202.uranium.core.common

import pl.karol202.uranium.core.element.Element

abstract class Props(val key: Any)

abstract class ChildrenProps(key: Any,
                             val children: List<Element<*>> = emptyList()) : Props(key)
