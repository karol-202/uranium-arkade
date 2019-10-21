package pl.karol202.uranium.swing.control.slider

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.core.context.InvalidateableContext
import pl.karol202.uranium.swing.SwingNativeComponent
import pl.karol202.uranium.swing.SwingNativeWrapper
import pl.karol202.uranium.swing.nativeComponent
import pl.karol202.uranium.swing.util.*
import javax.swing.JSlider
import javax.swing.event.ChangeListener

class SwingSlider(private val native: JSlider,
                  initialProps: Props) : SwingAbstractComponent<SwingSlider.Props>(initialProps)
{
	data class Props(override val key: Any = AutoKey,
	                 override val swingProps: SwingNativeComponent.Props = SwingNativeComponent.Props(),
	                 val value: Prop<Int> = Prop.NoValue,
	                 val minimum: Prop<Int> = Prop.NoValue,
	                 val maximum: Prop<Int> = Prop.NoValue,
	                 val majorTickSpacing: Prop<Int> = Prop.NoValue,
	                 val minorTickSpacing: Prop<Int> = Prop.NoValue,
	                 val extent: Prop<Int> = Prop.NoValue,
	                 val inverted: Prop<Boolean> = Prop.NoValue,
	                 val labelsPainted: Prop<Boolean> = Prop.NoValue,
	                 val ticksPainted: Prop<Boolean> = Prop.NoValue,
	                 val trackPainted: Prop<Boolean> = Prop.NoValue,
	                 val snapToTicks: Prop<Boolean> = Prop.NoValue,
	                 val orientation: Prop<Orientation> = Prop.NoValue,
	                 val labelTable: Prop<SliderLabelTable?> = Prop.NoValue,
	                 val onChange: Prop<(Int) -> Unit> = Prop.NoValue) : UProps,
	                                                                     SwingNativeComponent.PropsProvider<Props>,
	                                                                     PropsProvider<Props>
	{
		override val sliderProps = this

		override fun withSwingProps(builder: Builder<SwingNativeComponent.Props>) =
				copy(swingProps = swingProps.builder())

		override fun withSliderBarProps(builder: Builder<Props>) = builder()
	}

	interface PropsProvider<S : PropsProvider<S>> : UProps
	{
		val sliderProps: Props

		fun withSliderBarProps(builder: Builder<Props>): S
	}

	private val changeListener = ChangeListener { onChange() }

	override fun onAttach(parentContext: InvalidateableContext<SwingNativeWrapper>)
	{
		native.addChangeListener(changeListener)
	}

	override fun onDetach(parentContext: InvalidateableContext<SwingNativeWrapper>)
	{
		native.removeChangeListener(changeListener)
	}

	override fun SwingRenderBuilder.render()
	{
		+ nativeComponent(native = { native }, props = props.swingProps)
	}

	override fun onUpdate(previousProps: Props?) = native.apply {
		props.value.ifPresent { if(it != value) value = it }
		props.minimum.ifPresent { if(it != minimum) minimum = it }
		props.maximum.ifPresent { if(it != maximum) maximum = it }
		props.majorTickSpacing.ifPresent { majorTickSpacing = it }
		props.minorTickSpacing.ifPresent { minorTickSpacing = it }
		props.extent.ifPresent { extent = it }
		props.inverted.ifPresent { inverted = it }
		props.labelsPainted.ifPresent { paintLabels = it }
		props.ticksPainted.ifPresent { paintTicks = it }
		props.trackPainted.ifPresent { paintTrack = it }
		props.snapToTicks.ifPresent { snapToTicks = it }
		props.orientation.ifPresent { orientation = it.code }
		props.labelTable.ifPresent { labelTable = it?.createLabelTable(this) }
	}.unit

	private fun onChange()
	{
		if(native.value == props.value.value) return
		props.onChange.value?.invoke(native.value)
		invalidate()
	}
}

fun SwingRenderScope.slider(native: () -> JSlider = ::JSlider,
                            key: Any = AutoKey,
                            props: SwingSlider.Props = SwingSlider.Props(key)) =
		component({ SwingSlider(native(), it) }, props)

private typealias SSProvider<P> = SwingSlider.PropsProvider<P>
fun <P : SSProvider<P>> SwingElement<P>.withSliderBarProps(builder: Builder<SwingSlider.Props>) =
		withProps { withSliderBarProps(builder) }
fun <P : SSProvider<P>> SwingElement<P>.value(value: Int) =
		withSliderBarProps { copy(value = value.prop()) }
fun <P : SSProvider<P>> SwingElement<P>.minimum(minimum: Int) =
		withSliderBarProps { copy(minimum = minimum.prop()) }
fun <P : SSProvider<P>> SwingElement<P>.maximum(maximum: Int) =
		withSliderBarProps { copy(maximum = maximum.prop()) }
fun <P : SSProvider<P>> SwingElement<P>.majorTickSpacing(spacing: Int) =
		withSliderBarProps { copy(majorTickSpacing = spacing.prop()) }
fun <P : SSProvider<P>> SwingElement<P>.minorTickSpacing(spacing: Int) =
		withSliderBarProps { copy(minorTickSpacing = spacing.prop()) }
fun <P : SSProvider<P>> SwingElement<P>.extent(extent: Int) =
		withSliderBarProps { copy(extent = extent.prop()) }
fun <P : SSProvider<P>> SwingElement<P>.inverted(inverted: Boolean) =
		withSliderBarProps { copy(inverted = inverted.prop()) }
fun <P : SSProvider<P>> SwingElement<P>.labelsPainted(labels: Boolean) =
		withSliderBarProps { copy(labelsPainted = labels.prop()) }
fun <P : SSProvider<P>> SwingElement<P>.ticksPainted(ticks: Boolean) =
		withSliderBarProps { copy(ticksPainted = ticks.prop()) }
fun <P : SSProvider<P>> SwingElement<P>.trackPainted(track: Boolean) =
		withSliderBarProps { copy(trackPainted = track.prop()) }
fun <P : SSProvider<P>> SwingElement<P>.snapToTicks(snap: Boolean) =
		withSliderBarProps { copy(snapToTicks = snap.prop()) }
fun <P : SSProvider<P>> SwingElement<P>.orientation(orientation: Orientation) =
		withSliderBarProps { copy(orientation = orientation.prop()) }
fun <P : SSProvider<P>> SwingElement<P>.labelTable(table: SliderLabelTable?) =
		withSliderBarProps { copy(labelTable = table.prop()) }
fun <P : SSProvider<P>> SwingElement<P>.onChange(listener: (Int) -> Unit) =
		withSliderBarProps { copy(onChange = listener.prop()) }
