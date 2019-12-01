package pl.karol202.uranium.swing.layout.box

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.swing.layout.LayoutData
import pl.karol202.uranium.swing.layout.SwingLayout
import pl.karol202.uranium.swing.layout.layout
import pl.karol202.uranium.swing.layout.layoutData
import pl.karol202.uranium.swing.native.SwingNativeComponent
import pl.karol202.uranium.swing.util.*
import java.awt.Container
import java.awt.LayoutManager
import javax.swing.BoxLayout

class SwingBoxLayout(props: Props) : SwingAbstractComponent<SwingBoxLayout.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 override val layoutProps: SwingLayout.Props = SwingLayout.Props(),
	                 val axis: BoxAxis) : UProps,
	                                      SwingNativeComponent.PropsProvider<Props>,
	                                      SwingLayout.PropsProvider<Props>,
	                                      PropsProvider<Props>
	{
		override val swingProps = layoutProps.swingProps
		override val boxLayoutProps = this

		override fun withSwingProps(builder: Builder<SwingNativeComponent.Props>) =
				copy(layoutProps = layoutProps.withSwingProps(builder))

		override fun withLayoutProps(builder: Builder<SwingLayout.Props>) = copy(layoutProps = layoutProps.builder())

		override fun withBoxLayoutProps(builder: Builder<Props>) = builder()
	}

	interface PropsProvider<S : PropsProvider<S>> : UProps
	{
		val boxLayoutProps: Props

		fun withBoxLayoutProps(builder: Builder<Props>): S
	}

	data class Data(private val props: Props) : LayoutData<BoxLayout>
	{
		override fun createLayout(container: Container) = BoxLayout(container, props.axis.code)

		override fun updateLayout(container: Container, layout: LayoutManager) =
				(layout as? BoxLayout)?.takeIf { it.axis == props.axis.code } ?: createLayout(container)
	}

	override fun SwingRenderBuilder.render()
	{
		+ layout(props = props.layoutProps).layoutData(Data(props))
	}
}

fun SwingRenderScope.boxLayout(key: Any = AutoKey,
                               axis: BoxAxis,
                               block: SwingRenderBuilder.() -> Unit = {}) =
		boxLayout(props = SwingBoxLayout.Props(key = key, axis = axis)).content(block)

internal fun SwingRenderScope.boxLayout(props: SwingBoxLayout.Props) = component(::SwingBoxLayout, props)

private typealias SBLProvider<P> = SwingBoxLayout.PropsProvider<P>
fun <P : SBLProvider<P>> SwingElement<P>.withBoxLayoutProps(builder: Builder<SwingBoxLayout.Props>) =
		withProps { withBoxLayoutProps(builder) }
fun <P : SBLProvider<P>> SwingElement<P>.axis(axis: BoxAxis) =
		withBoxLayoutProps { copy(axis = axis) }
fun <P : SBLProvider<P>> SwingElement<P>.content(block: SwingRenderBuilder.() -> Unit) =
		withBoxLayoutProps { withSwingProps { copy(children = block.render()) } }
