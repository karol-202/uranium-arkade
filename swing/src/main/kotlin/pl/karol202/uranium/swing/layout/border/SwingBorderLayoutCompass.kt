package pl.karol202.uranium.swing.layout.border

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.core.render.RenderBuilder
import pl.karol202.uranium.swing.SNCProvider
import pl.karol202.uranium.swing.SwingNativeComponent
import pl.karol202.uranium.swing.SwingNativeWrapper
import pl.karol202.uranium.swing.constraints
import pl.karol202.uranium.swing.util.*
import java.awt.BorderLayout.*

class SwingBorderLayoutCompass(initialProps: Props) : SwingAbstractComponent<SwingBorderLayoutCompass.Props>(initialProps)
{
	data class Props(override val key: Any = AutoKey,
	                 override val borderLayoutBaseProps: SwingBorderLayoutBase.Props = SwingBorderLayoutBase.Props(),
	                 val center: Prop<SwingElement<*>?> = Prop.NoValue,
	                 val north: Prop<SwingElement<*>?> = Prop.NoValue,
	                 val west: Prop<SwingElement<*>?> = Prop.NoValue,
	                 val east: Prop<SwingElement<*>?> = Prop.NoValue,
	                 val south: Prop<SwingElement<*>?> = Prop.NoValue) : UProps,
	                                                                     SwingNativeComponent.PropsProvider<Props>,
	                                                                     SwingBorderLayoutBase.PropsProvider<Props>,
	                                                                     PropsProvider<Props>
	{
		override val swingProps = borderLayoutBaseProps.swingProps
		override val borderLayoutCompassProps = this

		override fun withSwingProps(builder: Builder<SwingNativeComponent.Props>) =
				copy(borderLayoutBaseProps = borderLayoutBaseProps.withSwingProps(builder))

		override fun withBorderLayoutBaseProps(builder: Builder<SwingBorderLayoutBase.Props>) =
				copy(borderLayoutBaseProps = borderLayoutBaseProps.builder())

		override fun withBorderLayoutCompassProps(builder: Builder<Props>) = builder()
	}

	interface PropsProvider<S : PropsProvider<S>> : UProps
	{
		val borderLayoutCompassProps: Props

		fun withBorderLayoutCompassProps(builder: Builder<Props>): S
	}

	override fun RenderBuilder<SwingNativeWrapper>.render()
	{
		+ borderLayoutBase(props = props.borderLayoutBaseProps.updateBaseProps())
	}

	private fun SwingBorderLayoutBase.Props.updateBaseProps() = withSwingProps { copy(children = createChildren()) }

	private fun createChildren() = listOf(props.center, props.north, props.west, props.east, props.south).mapNotNull { it.value }
}

fun SwingRenderScope.borderLayoutCompass(key: Any = AutoKey,
                                         props: SwingBorderLayoutCompass.Props = SwingBorderLayoutCompass.Props(key)) =
		component(::SwingBorderLayoutCompass, props)

private typealias SBLCProvider<P> = SwingBorderLayoutCompass.PropsProvider<P>
fun <P : SBLCProvider<P>> SwingElement<P>.withBorderLayoutCompassProps(builder: Builder<SwingBorderLayoutCompass.Props>) =
		withProps { withBorderLayoutCompassProps(builder) }
fun <P : SBLCProvider<P>, EP : SNCProvider<EP>> SwingElement<P>.center(element: SwingRenderScope.() -> SwingElement<EP>?) =
		withBorderLayoutCompassProps { copy(center = SwingEmptyRenderScope.element()?.constraints(CENTER).prop()) }
fun <P : SBLCProvider<P>, EP : SNCProvider<EP>> SwingElement<P>.north(element: SwingRenderScope.() -> SwingElement<EP>?) =
		withBorderLayoutCompassProps { copy(north = SwingEmptyRenderScope.element()?.constraints(NORTH).prop()) }
fun <P : SBLCProvider<P>, EP : SNCProvider<EP>> SwingElement<P>.west(element: SwingRenderScope.() -> SwingElement<EP>?) =
		withBorderLayoutCompassProps { copy(west = SwingEmptyRenderScope.element()?.constraints(WEST).prop()) }
fun <P : SBLCProvider<P>, EP : SNCProvider<EP>> SwingElement<P>.east(element: SwingRenderScope.() -> SwingElement<EP>?) =
		withBorderLayoutCompassProps { copy(east = SwingEmptyRenderScope.element()?.constraints(EAST).prop()) }
fun <P : SBLCProvider<P>, EP : SNCProvider<EP>> SwingElement<P>.south(element: SwingRenderScope.() -> SwingElement<EP>?) =
		withBorderLayoutCompassProps { copy(south = SwingEmptyRenderScope.element()?.constraints(SOUTH).prop()) }
