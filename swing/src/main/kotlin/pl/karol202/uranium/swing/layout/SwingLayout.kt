package pl.karol202.uranium.swing.layout

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.SwingContextImpl
import pl.karol202.uranium.swing.SwingNativeComponent
import pl.karol202.uranium.swing.nativeComponent
import pl.karol202.uranium.swing.util.*
import java.awt.LayoutManager
import javax.swing.JPanel

class SwingLayout(initialProps: Props) : SwingAbstractComponent<SwingLayout.Props>(initialProps)
{
	data class Props(override val key: Any = AutoKey,
	                 override val swingProps: SwingNativeComponent.Props = SwingNativeComponent.Props(),
	                 val layoutManager: Prop<LayoutManager> = Prop.NoValue) : UProps,
	                                                                          SwingNativeComponent.PropsProvider<Props>,
	                                                                          PropsProvider<Props>
	{
		override val layoutProps = this

		override fun withSwingProps(builder: Builder<SwingNativeComponent.Props>) = copy(swingProps = swingProps.builder())

		override fun withLayoutProps(builder: Builder<Props>) = builder()
	}

	interface PropsProvider<S : PropsProvider<S>> : UProps
	{
		val layoutProps: Props

		fun withLayoutProps(builder: Builder<Props>): S
	}

	private val native = JPanel(props.layoutManager.value)
	private val panelContext = SwingContextImpl(native)

	override fun SwingRenderBuilder.render()
	{
		+ nativeComponent(native = { native }, contextOverride = { panelContext }, props = props.swingProps)
	}

	override fun onUpdate(previousProps: Props) = native.apply {
		props.layoutManager.ifPresent { layout = it }
	}.unit
}

fun SwingRenderScope.layout(key: Any = AutoKey,
                            props: SwingLayout.Props = SwingLayout.Props(key)) = component(::SwingLayout, props)

private typealias SLProvider<P> = SwingLayout.PropsProvider<P>
fun <P : SLProvider<P>> SwingElement<P>.withLayoutProps(builder: Builder<SwingLayout.Props>) =
		withProps { withLayoutProps(builder) }
internal fun <P : SLProvider<P>> SwingElement<P>.layoutManager(layout: LayoutManager) =
		withLayoutProps { copy(layoutManager = layout.prop()) }
