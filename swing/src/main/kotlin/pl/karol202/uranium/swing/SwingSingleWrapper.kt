package pl.karol202.uranium.swing

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.core.util.RenderBuilder
import pl.karol202.uranium.swing.util.SwingAbstractComponent
import pl.karol202.uranium.swing.util.SwingElement
import pl.karol202.uranium.swing.util.SwingEmptyRenderScope
import pl.karol202.uranium.swing.util.SwingRenderScope

class SwingSingleWrapper(props: Props) : SwingAbstractComponent<SwingSingleWrapper.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 val child: SwingElement<*>) : UProps

	override fun RenderBuilder<SwingNativeWrapper>.render()
	{
		+ props.child
	}
}

fun SwingRenderScope.singleWrapper(key: Any = AutoKey,
                                   block: SwingRenderScope.() -> SwingElement<*>) =
		component(::SwingSingleWrapper, SwingSingleWrapper.Props(key = key, child = SwingEmptyRenderScope.block()))
