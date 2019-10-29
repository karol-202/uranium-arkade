package pl.karol202.uranium.swing.layout

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.native.SwingNativeComponent
import pl.karol202.uranium.swing.native.nativeComponent
import pl.karol202.uranium.swing.util.*
import javax.swing.JPanel

class SwingLayout(initialProps: Props) : SwingAbstractComponent<SwingLayout.Props>(initialProps)
{
	data class Props(override val key: Any = AutoKey,
	                 override val swingProps: SwingNativeComponent.Props = SwingNativeComponent.Props(),
	                 val layoutData: Prop<LayoutData<*>> = Prop.NoValue) : UProps,
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

	private val nativeComponent = JPanel()

	override fun onCreate()
	{
		props.layoutData.ifPresent { nativeComponent.layout = it.createLayout(nativeComponent) }
	}

	override fun SwingRenderBuilder.render()
	{
		+ nativeComponent(nativeComponent = { nativeComponent }, props = props.swingProps)
	}

	override fun onUpdate(previousProps: Props?)
	{
		props.layoutData.ifPresent { nativeComponent.layout = it.updateLayout(nativeComponent, nativeComponent.layout) }
	}
}

fun SwingRenderScope.layout(key: Any = AutoKey,
                            props: SwingLayout.Props = SwingLayout.Props(key)) = component(::SwingLayout, props)

private typealias SLProvider<P> = SwingLayout.PropsProvider<P>
fun <P : SLProvider<P>> SwingElement<P>.withLayoutProps(builder: Builder<SwingLayout.Props>) =
		withProps { withLayoutProps(builder) }
internal fun <P : SLProvider<P>> SwingElement<P>.layoutData(layout: LayoutData<*>) =
		withLayoutProps { copy(layoutData = layout.prop()) }
