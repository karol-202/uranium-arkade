package pl.karol202.uranium.swing.control.scrollpane

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.control.scrollbar.ScrollBarAxis.HORIZONTAL
import pl.karol202.uranium.swing.control.scrollbar.ScrollBarAxis.VERTICAL
import pl.karol202.uranium.swing.native.SwingNativeComponent
import pl.karol202.uranium.swing.native.nativeComponent
import pl.karol202.uranium.swing.util.*
import pl.karol202.uranium.swing.util.update
import java.awt.Component
import javax.swing.JScrollPane
import javax.swing.border.Border

class SwingScrollPaneBase(private val nativeComponent: JScrollPane,
                          initialProps: Props) : SwingAbstractComponent<SwingScrollPaneBase.Props>(initialProps)
{
	data class Props(override val key: Any = AutoKey,
	                 override val swingProps: SwingNativeComponent.Props = SwingNativeComponent.Props(),
	                 val content: Prop<SwingElement<*>?> = Prop.NoValue,
	                 val columnHeader: Prop<SwingElement<*>?> = Prop.NoValue,
	                 val rowHeader: Prop<SwingElement<*>?> = Prop.NoValue,
	                 val horizontalScrollPolicy: Prop<ScrollBarPolicy> = Prop.NoValue,
	                 val verticalScrollPolicy: Prop<ScrollBarPolicy> = Prop.NoValue,
	                 val wheelScrollingEnabled: Prop<Boolean> = Prop.NoValue,
	                 val viewportBorder: Prop<Border?> = Prop.NoValue) : UProps,
	                                                                            SwingNativeComponent.PropsProvider<Props>,
	                                                                            PropsProvider<Props>
	{
		override val scrollPaneBaseProps = this

		override fun withSwingProps(builder: Builder<SwingNativeComponent.Props>) = copy(swingProps = swingProps.builder())

		override fun withScrollPaneBaseProps(builder: Builder<Props>) = builder()
	}

	interface PropsProvider<S : PropsProvider<S>> : UProps
	{
		val scrollPaneBaseProps: Props

		fun withScrollPaneBaseProps(builder: Builder<Props>): S
	}

	private var contentRenderer = EmbeddedRenderer()
	private var columnHeaderRenderer = EmbeddedRenderer()
	private var rowHeaderRenderer = EmbeddedRenderer()

	override fun SwingRenderBuilder.render()
	{
		+ nativeComponent(nativeComponent = { nativeComponent }, props = props.swingProps)
	}

	override fun onUpdate(previousProps: Props?) = nativeComponent.update {
		props.content.ifPresent { content ->
			updateRenderer(contentRenderer, content, viewport?.view) { setViewportView(it) }
		}
		props.columnHeader.ifPresent { header ->
			updateRenderer(columnHeaderRenderer, header, columnHeader?.view) { setColumnHeaderView(it) }
		}
		props.rowHeader.ifPresent { header ->
			updateRenderer(rowHeaderRenderer, header, rowHeader?.view) { setRowHeaderView(it) }
		}
		props.horizontalScrollPolicy.ifPresent { horizontalScrollBarPolicy = it.codeProvider(HORIZONTAL) }
		props.verticalScrollPolicy.ifPresent { verticalScrollBarPolicy = it.codeProvider(VERTICAL) }
		props.wheelScrollingEnabled.ifPresent { isWheelScrollingEnabled = it }
		props.viewportBorder.ifPresent { viewportBorder = it }
	}

	private fun updateRenderer(renderer: EmbeddedRenderer, element: SwingElement<*>?,
	                           currentComponent: Component?, setter: (Component?) -> Unit)
	{
		if(element != null)
		{
			renderer.update(element)
			val component = renderer.component
			if(currentComponent != component) setter(component)
		}
		else if(currentComponent != null) setter(null)
	}
}

internal fun SwingRenderScope.scrollPaneBase(nativeComponent: () -> JScrollPane = ::JScrollPane,
                                             key: Any = AutoKey,
                                             props: SwingScrollPaneBase.Props = SwingScrollPaneBase.Props(key)) =
		component({ SwingScrollPaneBase(nativeComponent(), it) }, props)

private typealias SSPBProvider<P> = SwingScrollPaneBase.PropsProvider<P>
fun <P : SSPBProvider<P>> SwingElement<P>.withScrollPaneBaseProps(builder: Builder<SwingScrollPaneBase.Props>) =
		withProps { withScrollPaneBaseProps(builder) }
fun <P : SSPBProvider<P>> SwingElement<P>.content(content: SwingRenderScope.() -> SwingElement<*>) =
		withScrollPaneBaseProps { copy(content = SwingEmptyRenderScope.content().prop()) }
fun <P : SSPBProvider<P>> SwingElement<P>.columnHeader(header: SwingRenderScope.() -> SwingElement<*>) =
		withScrollPaneBaseProps { copy(columnHeader = SwingEmptyRenderScope.header().prop()) }
fun <P : SSPBProvider<P>> SwingElement<P>.rowHeader(header: SwingRenderScope.() -> SwingElement<*>) =
		withScrollPaneBaseProps { copy(rowHeader = SwingEmptyRenderScope.header().prop()) }
fun <P : SSPBProvider<P>> SwingElement<P>.horizontalScrollPolicy(policy: ScrollBarPolicy) =
		withScrollPaneBaseProps { copy(horizontalScrollPolicy = policy.prop()) }
fun <P : SSPBProvider<P>> SwingElement<P>.verticalScrollPolicy(policy: ScrollBarPolicy) =
		withScrollPaneBaseProps { copy(verticalScrollPolicy = policy.prop()) }
fun <P : SSPBProvider<P>> SwingElement<P>.wheelScrollingEnabled(enabled: Boolean) =
		withScrollPaneBaseProps { copy(wheelScrollingEnabled = enabled.prop()) }
fun <P : SSPBProvider<P>> SwingElement<P>.viewportBorder(border: Border?) =
		withScrollPaneBaseProps { copy(viewportBorder = border.prop()) }
