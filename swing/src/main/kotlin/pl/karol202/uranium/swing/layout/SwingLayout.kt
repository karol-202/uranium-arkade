package pl.karol202.uranium.swing.layout

import pl.karol202.uranium.core.util.RenderBuilder
import pl.karol202.uranium.core.util.buildComponent
import pl.karol202.uranium.swing.*
import java.awt.LayoutManager
import javax.swing.JPanel

class SwingLayout(layoutManager: LayoutManager,
                  props: SwingNativeComponent.Props) : SwingAbstractComponent<SwingNativeComponent.Props>(props)
{
	private val native = JPanel(layoutManager)

	override val context = SwingContextImpl(native)

	override fun RenderBuilder<SwingNative>.render()
	{
		+ nativeComponent(native = native, props = props)
	}
}

fun SwingRenderBuilder.layout(layoutManager: LayoutManager, props: SwingNativeComponent.Props) =
		buildComponent({ SwingLayout(layoutManager, it) }, props)
