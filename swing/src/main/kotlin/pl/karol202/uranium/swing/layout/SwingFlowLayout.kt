package pl.karol202.uranium.swing.layout

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.core.util.render
import pl.karol202.uranium.swing.SwingNativeComponent
import pl.karol202.uranium.swing.util.*
import java.awt.FlowLayout

class SwingFlowLayout(initialProps: Props) : SwingAbstractComponent<SwingFlowLayout.Props>(initialProps)
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
	                 override val layoutProps: SwingLayout.Props = SwingLayout.Props(),
	                 val align: Prop<Align> = Prop.NoValue,
	                 val alignOnBaseline: Prop<Boolean> = Prop.NoValue,
	                 val horizontalGap: Prop<Int> = Prop.NoValue,
	                 val verticalGap: Prop<Int> = Prop.NoValue) : UProps,
	                                                              SwingNativeComponent.PropsProvider<Props>,
                                                                  SwingLayout.PropsProvider<Props>,
	                                                              PropsProvider<Props>
	{
		override val swingProps = layoutProps.swingProps
		override val flowLayoutProps = this

		override fun withSwingProps(builder: Builder<SwingNativeComponent.Props>) =
				copy(layoutProps = layoutProps.withSwingProps(builder))

		override fun withLayoutProps(builder: Builder<SwingLayout.Props>) = copy(layoutProps = layoutProps.builder())

		override fun withFlowLayoutProps(builder: Builder<Props>) = builder()
	}

	interface PropsProvider<S : PropsProvider<S>> : UProps
	{
		val flowLayoutProps: Props

		fun withFlowLayoutProps(builder: Builder<Props>): S
	}

	private val layoutManager = FlowLayout()

	override fun SwingRenderBuilder.render()
	{
		+ layout(props = props.layoutProps).layoutManager(layoutManager)
	}

	override fun onUpdate(previousProps: Props) = layoutManager.apply {
		props.align.ifPresent { alignment = it.code }
		props.alignOnBaseline.ifPresent { alignOnBaseline = it }
		props.horizontalGap.ifPresent { hgap = it }
		props.verticalGap.ifPresent { vgap = it }
	}.unit
}

fun SwingRenderScope.flowLayout(key: Any = AutoKey,
                                props: SwingFlowLayout.Props = SwingFlowLayout.Props(key),
                                block: SwingRenderBuilder.() -> Unit = {}) =
		component(::SwingFlowLayout, props).content(block)

private typealias SFLProvider<P> = SwingFlowLayout.PropsProvider<P>
fun <P : SFLProvider<P>> SwingElement<P>.withFlowLayoutProps(builder: Builder<SwingFlowLayout.Props>) = withProps { withFlowLayoutProps(builder) }
fun <P : SFLProvider<P>> SwingElement<P>.align(align: SwingFlowLayout.Align) = withFlowLayoutProps { copy(align = align.prop()) }
fun <P : SFLProvider<P>> SwingElement<P>.alignOnBaseline(align: Boolean) = withFlowLayoutProps { copy(alignOnBaseline = align.prop()) }
fun <P : SFLProvider<P>> SwingElement<P>.horizontalGap(gap: Int) = withFlowLayoutProps { copy(horizontalGap = gap.prop()) }
fun <P : SFLProvider<P>> SwingElement<P>.verticalGap(gap: Int) = withFlowLayoutProps { copy(verticalGap = gap.prop()) }
fun <P : SFLProvider<P>> SwingElement<P>.content(block: SwingRenderBuilder.() -> Unit) = withFlowLayoutProps { withSwingProps { copy(children = block.render()) } }
