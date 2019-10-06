package pl.karol202.uranium.swing.layout.border

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.SwingNativeComponent
import pl.karol202.uranium.swing.layout.SwingLayout
import pl.karol202.uranium.swing.layout.layout
import pl.karol202.uranium.swing.layout.layoutManager
import pl.karol202.uranium.swing.util.*
import java.awt.BorderLayout

class SwingBorderLayoutBase(initialProps: Props) : SwingAbstractComponent<SwingBorderLayoutBase.Props>(initialProps)
{
	data class Props(override val key: Any = AutoKey,
	                 override val layoutProps: SwingLayout.Props = SwingLayout.Props(),
	                 val horizontalGap: Prop<Int> = Prop.NoValue,
	                 val verticalGap: Prop<Int> = Prop.NoValue) : UProps,
	                                                              SwingNativeComponent.PropsProvider<Props>,
                                                                  SwingLayout.PropsProvider<Props>,
	                                                              PropsProvider<Props>
	{
		override val swingProps = layoutProps.swingProps
		override val borderLayoutBaseProps = this

		override fun withSwingProps(builder: Builder<SwingNativeComponent.Props>) =
				copy(layoutProps = layoutProps.withSwingProps(builder))

		override fun withLayoutProps(builder: Builder<SwingLayout.Props>) = copy(layoutProps = layoutProps.builder())

		override fun withBorderLayoutBaseProps(builder: Builder<Props>) = builder()
	}

	interface PropsProvider<S : PropsProvider<S>> : UProps
	{
		val borderLayoutBaseProps: Props

		fun withBorderLayoutBaseProps(builder: Builder<Props>): S
	}

	private val layoutManager = BorderLayout()

	override fun SwingRenderBuilder.render()
	{
		+ layout(props = props.layoutProps).layoutManager(layoutManager)
	}

	override fun onUpdate(previousProps: Props) = layoutManager.apply {
		props.horizontalGap.ifPresent { hgap = it }
		props.verticalGap.ifPresent { vgap = it }
	}.unit
}

fun SwingRenderScope.borderLayoutBase(key: Any = AutoKey,
                                      props: SwingBorderLayoutBase.Props = SwingBorderLayoutBase.Props(key)) =
		component(::SwingBorderLayoutBase, props)

private typealias SBLProvider<P> = SwingBorderLayoutBase.PropsProvider<P>
fun <P : SBLProvider<P>> SwingElement<P>.withBorderLayoutBaseProps(builder: Builder<SwingBorderLayoutBase.Props>) = withProps { withBorderLayoutBaseProps(builder) }
fun <P : SBLProvider<P>> SwingElement<P>.horizontalGap(gap: Int) = withBorderLayoutBaseProps { copy(horizontalGap = gap.prop()) }
fun <P : SBLProvider<P>> SwingElement<P>.verticalGap(gap: Int) = withBorderLayoutBaseProps { copy(verticalGap = gap.prop()) }
