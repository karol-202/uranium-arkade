package pl.karol202.uranium.swing.layout

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
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

	data class Props(override val key: Any = AutoKey,
	                 override val swingProps: SwingNativeComponent.Props = SwingNativeComponent.Props(),
	                 val align: Prop<Align> = Prop.NoValue,
	                 val alignOnBaseline: Prop<Boolean> = Prop.NoValue,
	                 val horizontalGap: Prop<Int> = Prop.NoValue,
	                 val verticalGap: Prop<Int> = Prop.NoValue) : UProps,
	                                                              SwingNativeComponent.PropsProvider<Props>,
                                                                  PropsProvider<Props>
	{
		override val flowLayoutProps = this

		override fun withSwingProps(builder: Builder<SwingNativeComponent.Props>) = copy(swingProps = swingProps.builder())

		override fun withFlowLayoutProps(builder: Builder<Props>) = builder()
	}

	interface PropsProvider<S : PropsProvider<S>> : UProps
	{
		val flowLayoutProps: Props

		fun withFlowLayoutProps(builder: Builder<Props>): S
	}

	private val layoutManager = FlowLayout()

	override fun RenderBuilder<SwingNative>.render()
	{
		+ layout(layoutManager = { layoutManager }, props = props.swingProps)
		onUpdate()
	}

	private fun onUpdate() = layoutManager.apply {
		props.align.ifPresent { alignment = it.code }
		props.alignOnBaseline.ifPresent { alignOnBaseline = it }
		props.horizontalGap.ifPresent { hgap = it }
		props.verticalGap.ifPresent { vgap = it }
	}
}

private typealias Provider<P> = SwingFlowLayout.PropsProvider<P>
fun <P : Provider<P>> SwingElement<P>.withFlowLayoutProps(builder: Builder<SwingFlowLayout.Props>) =
		withProps { withFlowLayoutProps(builder) }

fun SwingRenderBuilder.flowLayout(key: Any = AutoKey,
                                  props: SwingFlowLayout.Props = SwingFlowLayout.Props(key),
                                  block: SwingRenderBuilder.() -> Unit = {}) =
		component(::SwingFlowLayout, props).content(block)
fun <P : Provider<P>> SwingElement<P>.align(align: SwingFlowLayout.Align) = withFlowLayoutProps { copy(align = align.prop()) }
fun <P : Provider<P>> SwingElement<P>.alignOnBaseline(align: Boolean) = withFlowLayoutProps { copy(alignOnBaseline = align.prop()) }
fun <P : Provider<P>> SwingElement<P>.horizontalGap(gap: Int) = withFlowLayoutProps { copy(horizontalGap = gap.prop()) }
fun <P : Provider<P>> SwingElement<P>.verticalGap(gap: Int) = withFlowLayoutProps { copy(verticalGap = gap.prop()) }
fun <P : Provider<P>> SwingElement<P>.content(block: SwingRenderBuilder.() -> Unit) = withFlowLayoutProps { withSwingProps { copy(children = block.render()) } }
