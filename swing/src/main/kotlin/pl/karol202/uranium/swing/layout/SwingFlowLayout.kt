package pl.karol202.uranium.swing.layout

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.BaseProps
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.util.*
import pl.karol202.uranium.swing.*
import java.awt.FlowLayout

class SwingFlowLayout(props: Props) : SwingAbstractComponent<SwingFlowLayout.Props>(props)
{
	enum class Align(val code: Int)
	{
		LEFT(FlowLayout.LEFT),
		CENTER(FlowLayout.CENTER),
		RIGHT(FlowLayout.RIGHT),
		LEADING(FlowLayout.LEADING),
		TRAILING(FlowLayout.TRAILING)
	}

	data class Props(override val swingProps: SwingNativeComponent.Props,
	                 val align: Prop<Align> = Prop.NoValue,
	                 val alignOnBaseline: Prop<Boolean> = Prop.NoValue,
	                 val horizontalGap: Prop<Int> = Prop.NoValue,
	                 val verticalGap: Prop<Int> = Prop.NoValue) : UProps by swingProps,
	                                                              SwingNativeComponent.Props.Provider<Props>
	{
		override fun withSwingProps(builder: SwingNativeComponent.Props.() -> SwingNativeComponent.Props) =
				copy(swingProps = swingProps.builder())
	}

	private val layoutManager = FlowLayout()

	override fun RenderBuilder<SwingNative>.render()
	{
		+ layout(layoutManager = layoutManager, props = props.swingProps)
		onUpdate()
	}

	private fun onUpdate() = layoutManager.apply {
		props.align.ifPresent { alignment = it.code }
		props.alignOnBaseline.ifPresent { alignOnBaseline = it }
		props.horizontalGap.ifPresent { hgap = it }
		props.verticalGap.ifPresent { vgap = it }
	}
}

private typealias FlowLayoutBuilder = SwingComponentBuilder<SwingFlowLayout.Props>

fun SwingRenderBuilder.flowLayout(key: Any = AutoKey, block: SwingRenderBuilder.() -> Unit = {}) =
		buildComponent(::SwingFlowLayout, SwingFlowLayout.Props(SwingNativeComponent.Props(BaseProps(key)))).content(block)
fun FlowLayoutBuilder.align(align: SwingFlowLayout.Align) = withProps { copy(align = align.prop()) }
fun FlowLayoutBuilder.alignOnBaseline(align: Boolean) = withProps { copy(alignOnBaseline = align.prop()) }
fun FlowLayoutBuilder.horizontalGap(gap: Int) = withProps { copy(horizontalGap = gap.prop()) }
fun FlowLayoutBuilder.verticalGap(gap: Int) = withProps { copy(verticalGap = gap.prop()) }
fun FlowLayoutBuilder.content(block: SwingRenderBuilder.() -> Unit) = withProps { withSwingProps { copy(children = block.render()) } }
