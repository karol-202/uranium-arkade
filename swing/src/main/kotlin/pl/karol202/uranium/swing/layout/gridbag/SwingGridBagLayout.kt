package pl.karol202.uranium.swing.layout.gridbag

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.layout.LayoutData
import pl.karol202.uranium.swing.layout.SwingLayout
import pl.karol202.uranium.swing.layout.layout
import pl.karol202.uranium.swing.layout.layoutData
import pl.karol202.uranium.swing.native.SwingNativeComponent
import pl.karol202.uranium.swing.util.*
import java.awt.Container
import java.awt.GridBagLayout
import java.awt.LayoutManager

class SwingGridBagLayout(props: Props) : SwingAbstractComponent<SwingGridBagLayout.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 override val layoutProps: SwingLayout.Props = SwingLayout.Props()) :
			UProps,
			SwingNativeComponent.PropsProvider<Props>,
			SwingLayout.PropsProvider<Props>,
			PropsProvider<Props>
	{
		override val swingProps = layoutProps.swingProps
		override val gridBagLayoutProps = this

		override fun withSwingProps(builder: Builder<SwingNativeComponent.Props>) =
				copy(layoutProps = layoutProps.withSwingProps(builder))

		override fun withLayoutProps(builder: Builder<SwingLayout.Props>) = copy(layoutProps = layoutProps.builder())

		override fun withGridBagLayoutProps(builder: Builder<Props>) = builder()
	}

	interface PropsProvider<S : PropsProvider<S>> : UProps
	{
		val gridBagLayoutProps: Props

		fun withGridBagLayoutProps(builder: Builder<Props>): S
	}

	data class Data(private val props: Props) : LayoutData<GridBagLayout>
	{
		override fun createLayout(container: Container) = GridBagLayout()

		override fun updateLayout(container: Container, layout: LayoutManager) =
				(layout as? GridBagLayout) ?: createLayout(container)
	}

	override fun SwingRenderBuilder.render()
	{
		+ layout(props = props.layoutProps).layoutData(Data(props))
	}
}

fun SwingRenderScope.gridBagLayout(key: Any = AutoKey,
                                   block: SwingGridBagBuilder.() -> Unit) =
		component(::SwingGridBagLayout, SwingGridBagLayout.Props(key)).content(block)

internal fun SwingRenderScope.gridBagLayout(props: SwingGridBagLayout.Props) = component(::SwingGridBagLayout, props)

private typealias SGBLProvider<P> = SwingGridBagLayout.PropsProvider<P>
fun <P : SGBLProvider<P>> SwingElement<P>.withGridBagLayoutProps(builder: Builder<SwingGridBagLayout.Props>) =
		withProps { withGridBagLayoutProps(builder) }
fun <P : SGBLProvider<P>> SwingElement<P>.content(block: SwingGridBagBuilder.() -> Unit) =
		withGridBagLayoutProps { withSwingProps { copy(children = block.render()) } }
