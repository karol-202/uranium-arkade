package pl.karol202.uranium.swing.layout.border

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.native.SNCProvider
import pl.karol202.uranium.swing.native.SwingNativeComponent
import pl.karol202.uranium.swing.native.constraints
import pl.karol202.uranium.swing.util.*
import java.awt.BorderLayout.*

class SwingBorderLayoutPage(initialProps: Props) : SwingAbstractComponent<SwingBorderLayoutPage.Props>(initialProps)
{
	data class Props(override val key: Any = AutoKey,
	                 override val borderLayoutBaseProps: SwingBorderLayoutBase.Props = SwingBorderLayoutBase.Props(),
	                 val center: Prop<SwingElement<*>?> = Prop.NoValue,
	                 val pageStart: Prop<SwingElement<*>?> = Prop.NoValue,
	                 val lineStart: Prop<SwingElement<*>?> = Prop.NoValue,
	                 val lineEnd: Prop<SwingElement<*>?> = Prop.NoValue,
	                 val pageEnd: Prop<SwingElement<*>?> = Prop.NoValue) : UProps,
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

		override fun withBorderLayoutPageProps(builder: Builder<Props>) = builder()
	}

	interface PropsProvider<S : PropsProvider<S>> : UProps
	{
		val borderLayoutCompassProps: Props

		fun withBorderLayoutPageProps(builder: Builder<Props>): S
	}

	override fun SwingRenderBuilder.render()
	{
		+ borderLayoutBase(props = props.borderLayoutBaseProps.updateBaseProps())
	}

	private fun SwingBorderLayoutBase.Props.updateBaseProps() = withSwingProps { copy(children = createChildren()) }

	private fun createChildren() =
			listOf(props.center, props.pageStart, props.lineStart, props.lineEnd, props.pageEnd).mapNotNull { it.value }
}

fun SwingRenderScope.borderLayout(key: Any = AutoKey) = borderLayoutPage(props = SwingBorderLayoutPage.Props(key))

internal fun SwingRenderScope.borderLayoutPage(props: SwingBorderLayoutPage.Props) = component(::SwingBorderLayoutPage, props)

private typealias SBLPProvider<P> = SwingBorderLayoutPage.PropsProvider<P>
fun <P : SBLPProvider<P>> SwingElement<P>.withBorderLayoutPageProps(builder: Builder<SwingBorderLayoutPage.Props>) =
		withProps { withBorderLayoutPageProps(builder) }
fun <P : SBLPProvider<P>, EP : SNCProvider<EP>> SwingElement<P>.center(element: SwingRenderScope.() -> SwingElement<EP>?) =
		withBorderLayoutPageProps { copy(center = SwingEmptyRenderScope.element()?.constraints(CENTER).prop()) }
fun <P : SBLPProvider<P>, EP : SNCProvider<EP>> SwingElement<P>.pageStart(element: SwingRenderScope.() -> SwingElement<EP>?) =
		withBorderLayoutPageProps { copy(pageStart = SwingEmptyRenderScope.element()?.constraints(PAGE_START).prop()) }
fun <P : SBLPProvider<P>, EP : SNCProvider<EP>> SwingElement<P>.lineStart(element: SwingRenderScope.() -> SwingElement<EP>?) =
		withBorderLayoutPageProps { copy(lineStart = SwingEmptyRenderScope.element()?.constraints(LINE_START).prop()) }
fun <P : SBLPProvider<P>, EP : SNCProvider<EP>> SwingElement<P>.lineEnd(element: SwingRenderScope.() -> SwingElement<EP>?) =
		withBorderLayoutPageProps { copy(lineEnd = SwingEmptyRenderScope.element()?.constraints(LINE_END).prop()) }
fun <P : SBLPProvider<P>, EP : SNCProvider<EP>> SwingElement<P>.pageEnd(element: SwingRenderScope.() -> SwingElement<EP>?) =
		withBorderLayoutPageProps { copy(pageEnd = SwingEmptyRenderScope.element()?.constraints(PAGE_END).prop()) }
