package pl.karol202.uranium.swing.control.progress

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.SwingNativeComponent
import pl.karol202.uranium.swing.nativeComponent
import pl.karol202.uranium.swing.util.*
import javax.swing.JProgressBar

class SwingProgressBar(private val native: JProgressBar,
                       initialProps: Props) : SwingAbstractComponent<SwingProgressBar.Props>(initialProps)
{
	data class Props(override val key: Any = AutoKey,
	                 override val swingProps: SwingNativeComponent.Props = SwingNativeComponent.Props(),
	                 val value: Prop<Int> = Prop.NoValue,
	                 val minimum: Prop<Int> = Prop.NoValue,
	                 val maximum: Prop<Int> = Prop.NoValue,
	                 val indeterminate: Prop<Boolean> = Prop.NoValue,
	                 val borderPainted: Prop<Boolean> = Prop.NoValue,
	                 val stringPainted: Prop<Boolean> = Prop.NoValue,
	                 val orientation: Prop<Orientation> = Prop.NoValue,
	                 val string: Prop<String?> = Prop.NoValue) : UProps,
	                                                             SwingNativeComponent.PropsProvider<Props>,
	                                                             PropsProvider<Props>
	{
		override val progressBarProps = this

		override fun withSwingProps(builder: Builder<SwingNativeComponent.Props>) =
				copy(swingProps = swingProps.builder())

		override fun withProgressBarProps(builder: Builder<Props>) = builder()
	}

	interface PropsProvider<S : PropsProvider<S>> : UProps
	{
		val progressBarProps: Props

		fun withProgressBarProps(builder: Builder<Props>): S
	}

	override fun SwingRenderBuilder.render()
	{
		+ nativeComponent(native = { native }, props = props.swingProps)
	}

	override fun onUpdate(previousProps: Props?) = native.apply {
		props.value.ifPresent { value = it }
		props.minimum.ifPresent { minimum = it }
		props.maximum.ifPresent { maximum = it }
		props.indeterminate.ifPresent { isIndeterminate = it }
		props.borderPainted.ifPresent { isBorderPainted = it }
		props.stringPainted.ifPresent { isStringPainted = it }
		props.orientation.ifPresent { orientation = it.code }
		props.string.ifPresent { string = it }
	}.unit
}

fun SwingRenderScope.progressBar(native: () -> JProgressBar = ::JProgressBar,
                                 key: Any = AutoKey,
                                 props: SwingProgressBar.Props = SwingProgressBar.Props(key)) =
		component({ SwingProgressBar(native(), it) }, props)

private typealias SPBProvider<P> = SwingProgressBar.PropsProvider<P>
fun <P : SPBProvider<P>> SwingElement<P>.withProgressBarProps(builder: Builder<SwingProgressBar.Props>) =
		withProps { withProgressBarProps(builder) }
fun <P : SPBProvider<P>> SwingElement<P>.value(value: Int) =
		withProgressBarProps { copy(value = value.prop()) }
fun <P : SPBProvider<P>> SwingElement<P>.minimum(minimum: Int) =
		withProgressBarProps { copy(minimum = minimum.prop()) }
fun <P : SPBProvider<P>> SwingElement<P>.maximum(maximum: Int) =
		withProgressBarProps { copy(maximum = maximum.prop()) }
fun <P : SPBProvider<P>> SwingElement<P>.indeterminate(indeterminate: Boolean) =
		withProgressBarProps { copy(indeterminate = indeterminate.prop()) }
fun <P : SPBProvider<P>> SwingElement<P>.borderPainted(border: Boolean) =
		withProgressBarProps { copy(borderPainted = border.prop()) }
fun <P : SPBProvider<P>> SwingElement<P>.stringPainted(string: Boolean) =
		withProgressBarProps { copy(stringPainted = string.prop()) }
fun <P : SPBProvider<P>> SwingElement<P>.orientation(orientation: Orientation) =
		withProgressBarProps { copy(orientation = orientation.prop()) }
fun <P : SPBProvider<P>> SwingElement<P>.string(string: String) =
		withProgressBarProps { copy(string = string.prop()) }
