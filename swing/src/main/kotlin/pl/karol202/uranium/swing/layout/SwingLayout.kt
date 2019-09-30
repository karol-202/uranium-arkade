package pl.karol202.uranium.swing.layout

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.core.util.RenderBuilder
import pl.karol202.uranium.swing.*
import java.awt.LayoutManager
import javax.swing.JPanel

class SwingLayout(layoutManager: LayoutManager,
                  props: SwingNativeComponent.Props) : SwingAbstractComponent<SwingNativeComponent.Props>(props)
{
	private val native = JPanel(layoutManager)
	private val panelContext = SwingContextImpl(native)

	override fun RenderBuilder<SwingNative>.render()
	{
		+ nativeComponent(native = { native }, contextOverride = { panelContext }, props = props)
	}
}

fun SwingRenderBuilder.layout(layoutManager: () -> LayoutManager,
                              key: Any = AutoKey,
                              props: SwingNativeComponent.Props = SwingNativeComponent.Props(key)) =
		component({ SwingLayout(layoutManager(), it) }, props)
