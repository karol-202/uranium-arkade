package pl.karol202.uranium.swing.control.scrollpane

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.native.SwingNativeComponent
import pl.karol202.uranium.swing.native.nativeComponent
import pl.karol202.uranium.swing.util.*
import pl.karol202.uranium.swing.util.update
import javax.swing.JScrollPane

class SwingScrollPane(private val nativeComponent: JScrollPane,
                      initialProps: Props) : SwingAbstractComponent<SwingScrollPane.Props>(initialProps)
{
	data class Props(override val key: Any = AutoKey,
	                 override val swingProps: SwingNativeComponent.Props = SwingNativeComponent.Props()) : UProps,
	                                                                                                       SwingNativeComponent.PropsProvider<Props>,
	                                                                                                       PropsProvider<Props>
	{
		override val scrollPaneProps = this

		override fun withSwingProps(builder: Builder<SwingNativeComponent.Props>) = copy(swingProps = swingProps.builder())

		override fun withScrollPaneProps(builder: Builder<Props>) = builder()
	}

	interface PropsProvider<S : PropsProvider<S>> : UProps
	{
		val scrollPaneProps: Props

		fun withScrollPaneProps(builder: Builder<Props>): S
	}

	override fun SwingRenderBuilder.render()
	{
		+ nativeComponent(nativeComponent = { nativeComponent }, props = props.swingProps)
	}

	override fun onUpdate(previousProps: Props?) = nativeComponent.update {

	}
}

fun SwingRenderScope.scrollPane(nativeComponent: () -> JScrollPane = ::JScrollPane,
                                key: Any = AutoKey,
                                props: SwingScrollPane.Props = SwingScrollPane.Props(key)) =
		component({ SwingScrollPane(nativeComponent(), it) }, props)

private typealias SSPProvider<P> = SwingScrollPane.PropsProvider<P>
fun <P : SSPProvider<P>> SwingElement<P>.withScrollPaneProps(builder: Builder<SwingScrollPane.Props>) =
		withProps { withScrollPaneProps(builder) }
fun <P : SSPProvider<P>> SwingElement<P>.echoChar(char: Char?) =
		withScrollPaneProps { copy(echoChar = char.prop()) }
fun <P : SSPProvider<P>> SwingElement<P>.onPasswordApply(listener: (CharArray) -> Unit) =
		withScrollPaneProps { copy(onPasswordApply = listener.prop()) }
