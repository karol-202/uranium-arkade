package pl.karol202.uranium.swing.layout.grid

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
import java.awt.GridLayout
import java.awt.LayoutManager

class SwingGridLayout(props: Props) : SwingAbstractComponent<SwingGridLayout.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 override val layoutProps: SwingLayout.Props = SwingLayout.Props(),
	                 val rows: Prop<Int> = Prop.NoValue,
	                 val columns: Prop<Int> = Prop.NoValue,
	                 val horizontalGap: Prop<Int> = Prop.NoValue,
	                 val verticalGap: Prop<Int> = Prop.NoValue) : UProps,
	                                                              SwingNativeComponent.PropsProvider<Props>,
	                                                              SwingLayout.PropsProvider<Props>,
	                                                              PropsProvider<Props>
	{
		override val swingProps = layoutProps.swingProps
		override val gridLayoutProps = this

		override fun withSwingProps(builder: Builder<SwingNativeComponent.Props>) =
				copy(layoutProps = layoutProps.withSwingProps(builder))

		override fun withLayoutProps(builder: Builder<SwingLayout.Props>) = copy(layoutProps = layoutProps.builder())

		override fun withGridLayoutProps(builder: Builder<Props>) = builder()
	}

	interface PropsProvider<S : PropsProvider<S>> : UProps
	{
		val gridLayoutProps: Props

		fun withGridLayoutProps(builder: Builder<Props>): S
	}

	data class Data(private val props: Props) : LayoutData<GridLayout>
	{
		override fun createLayout(container: Container) = GridLayout()

		override fun updateLayout(container: Container, layout: LayoutManager) = (layout as? GridLayout)?.apply {
			props.rows.ifPresent { rows = it }
			props.columns.ifPresent { columns = it }
			props.horizontalGap.ifPresent { hgap = it }
			props.verticalGap.ifPresent { vgap = it }
		} ?: createLayout(container)
	}

	override fun SwingRenderBuilder.render()
	{
		+ layout(props = props.layoutProps).layoutData(Data(props))
	}
}

fun SwingRenderScope.gridLayout(key: Any = AutoKey) = gridLayout(props = SwingGridLayout.Props(key))

internal fun SwingRenderScope.gridLayout(props: SwingGridLayout.Props) = component(::SwingGridLayout, props)

private typealias SGLProvider<P> = SwingGridLayout.PropsProvider<P>
fun <P : SGLProvider<P>> SwingElement<P>.withGridLayoutProps(builder: Builder<SwingGridLayout.Props>) =
		withProps { withGridLayoutProps(builder) }
internal fun <P : SGLProvider<P>> SwingElement<P>.content(builder: GridLayoutBuilder) = withGridLayoutProps {
	copy(rows = builder.rows.prop(), columns = builder.columns.prop()).withSwingProps { copy(children = builder.elements) }
}
fun <P : SGLProvider<P>> SwingElement<P>.contentRows(block: GridLayoutRowsBuilder.() -> Unit) =
		content(GridLayoutRowsBuilder().apply(block))
fun <P : SGLProvider<P>> SwingElement<P>.contentColumns(block: GridLayoutColumnsBuilder.() -> Unit) =
		content(GridLayoutColumnsBuilder().apply(block))
