package pl.karol202.uranium.core.element

import pl.karol202.uranium.core.common.Props
import pl.karol202.uranium.core.common.WithKey
import pl.karol202.uranium.core.component.Component

interface Element<P : Props> : WithKey
{
	val props: P

	override val key get() = props.key

	fun createComponent(): Component
}
