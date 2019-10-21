package pl.karol202.uranium.swing.layout.flow

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.swing.SwingNativeComponent
import pl.karol202.uranium.swing.layout.LayoutData
import pl.karol202.uranium.swing.layout.SwingLayout
import pl.karol202.uranium.swing.layout.layout
import pl.karol202.uranium.swing.layout.layoutData
import pl.karol202.uranium.swing.util.*
import java.awt.FlowLayout
import java.awt.LayoutManager

class SwingFlowLayout(initialProps: Props) : SwingAbstractComponent<SwingFlowLayout.Props>(initialProps)
{
	data class Props(override val key: Any = AutoKey,
	                 override val layoutProps: SwingLayout.Props = SwingLayout.Props(),
	                 val align: Prop<FlowAlign> = Prop.NoValue,
	                 val alignOnBaseline: Prop<Boolean> = Prop.NoValue,
	                 val horizontalGap: Prop<Int> = Prop.NoValue,
	                 val verticalGap: Prop<Int> = Prop.NoValue) : UProps,
	                                                              SwingNativeComponent.PropsProvider<Props>, SwingLayout.PropsProvider<Props>, PropsProvider<Props>
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

	data class Data(private val props: Props) : LayoutData<FlowLayout>
	{
		override fun createLayout(container: SwingContainer): FlowLayout = updateLayout(container, FlowLayout())

		override fun updateLayout(container: SwingContainer, layout: LayoutManager) = (layout as? FlowLayout)?.apply {
			props.align.ifPresent { alignment = it.code }
			props.alignOnBaseline.ifPresent { alignOnBaseline = it }
			props.horizontalGap.ifPresent { hgap = it }
			props.verticalGap.ifPresent { vgap = it }
		} ?: createLayout(container)
	}

	override fun SwingRenderBuilder.render()
	{
		+ layout(props = props.layoutProps).layoutData(Data(props))
	}
}

fun SwingRenderScope.flowLayout(key: Any = AutoKey,
                                props: SwingFlowLayout.Props = SwingFlowLayout.Props(key),
                                block: SwingRenderBuilder.() -> Unit = {}) =
		component(::SwingFlowLayout, props).content(block)

private typealias SFLProvider<P> = SwingFlowLayout.PropsProvider<P>
fun <P : SFLProvider<P>> SwingElement<P>.withFlowLayoutProps(builder: Builder<SwingFlowLayout.Props>) = withProps { withFlowLayoutProps(builder) }
fun <P : SFLProvider<P>> SwingElement<P>.align(align: FlowAlign) = withFlowLayoutProps { copy(align = align.prop()) }
fun <P : SFLProvider<P>> SwingElement<P>.alignOnBaseline(align: Boolean) = withFlowLayoutProps { copy(alignOnBaseline = align.prop()) }
fun <P : SFLProvider<P>> SwingElement<P>.horizontalGap(gap: Int) = withFlowLayoutProps { copy(horizontalGap = gap.prop()) }
fun <P : SFLProvider<P>> SwingElement<P>.verticalGap(gap: Int) = withFlowLayoutProps { copy(verticalGap = gap.prop()) }
fun <P : SFLProvider<P>> SwingElement<P>.content(block: SwingRenderBuilder.() -> Unit) = withFlowLayoutProps { withSwingProps { copy(children = block.render()) } }
