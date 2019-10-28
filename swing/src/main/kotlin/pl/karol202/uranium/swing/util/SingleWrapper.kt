package pl.karol202.uranium.swing.util

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component

class SwingSingleWrapper(props: Props) : SwingAbstractComponent<SwingSingleWrapper.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 val child: SwingElement<*>) : UProps

	override fun SwingRenderBuilder.render()
	{
		+ props.child
	}
}

fun SwingRenderScope.singleWrapper(key: Any = AutoKey,
                                   block: SwingRenderScope.() -> SwingElement<*>) =
		component(::SwingSingleWrapper, SwingSingleWrapper.Props(key = key, child = SwingEmptyRenderScope.block()))
