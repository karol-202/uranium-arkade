package pl.karol202.uranium.core.element

import pl.karol202.uranium.core.common.HasProps
import pl.karol202.uranium.core.common.Props
import pl.karol202.uranium.core.component.Component

interface Element<P : Props> : HasProps<P>
{
	fun createComponent(): Component<P>
}
