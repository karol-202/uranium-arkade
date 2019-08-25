package pl.karol202.uranium.core.component

import pl.karol202.uranium.core.common.*

interface Component<P : Props> : Renderable, Attachable, Detachable, HasProps<P>
{
	override var props: P
}
