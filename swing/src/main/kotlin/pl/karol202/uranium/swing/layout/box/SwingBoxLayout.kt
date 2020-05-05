package pl.karol202.uranium.swing.layout.box

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderScope
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.swing.*
import pl.karol202.uranium.swing.Builder
import pl.karol202.uranium.swing.component.SwingAbstractAppComponent
import pl.karol202.uranium.swing.layout.layout
import pl.karol202.uranium.swing.component.SwingContainerComponent
import java.awt.Container
import java.awt.LayoutManager
import javax.swing.BoxLayout

class SwingBoxLayout(props: Props) : SwingAbstractAppComponent<SwingBoxLayout.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 override val swingProps: SwingContainerComponent.Props = SwingContainerComponent.Props(),
	                 val content: List<SwingElement<*>> = emptyList(),
	                 val axis: BoxAxis) : UProps,
	                                      SwingContainerComponent.PropsProvider<Props>,
	                                      PropsProvider<Props>
	{
		override val boxLayoutProps = this

		override fun withSwingProps(builder: Builder<SwingContainerComponent.Props>) =
				copy(swingProps = swingProps.builder())

		override fun withBoxLayoutProps(builder: Builder<Props>) = builder()
	}

	interface PropsProvider<S : PropsProvider<S>> : UProps
	{
		val boxLayoutProps: Props

		fun withBoxLayoutProps(builder: Builder<Props>): S
	}

	override fun URenderScope<Swing>.render() = layout(swingProps = props.swingProps,
	                                                   content = props.content,
	                                                   layoutUpdater = ::updateLayout)

	private fun updateLayout(container: Container, layout: LayoutManager?) =
			(layout as? BoxLayout)?.takeIf { it.axis == props.axis.code } ?: BoxLayout(container, props.axis.code)
}

fun SwingRenderScope.boxLayout(key: Any = AutoKey,
                               axis: BoxAxis,
                               block: SwingRenderBuilder.() -> Unit) =
		boxLayout(props = SwingBoxLayout.Props(key = key, axis = axis)).content(block)

internal fun SwingRenderScope.boxLayout(props: SwingBoxLayout.Props) = component(::SwingBoxLayout, props)

private typealias SBLProvider<P> = SwingBoxLayout.PropsProvider<P>
fun <P : SBLProvider<P>> SwingElement<P>.withBoxLayoutProps(builder: Builder<SwingBoxLayout.Props>) =
		withProps { withBoxLayoutProps(builder) }
fun <P : SBLProvider<P>> SwingElement<P>.axis(axis: BoxAxis) =
		withBoxLayoutProps { copy(axis = axis) }
fun <P : SBLProvider<P>> SwingElement<P>.content(block: SwingRenderBuilder.() -> Unit) =
		withBoxLayoutProps { withSwingProps { copy(children = block.render()) } }
