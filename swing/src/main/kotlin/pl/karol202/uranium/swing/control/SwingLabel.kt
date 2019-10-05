package pl.karol202.uranium.swing.control

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.SwingNativeComponent
import pl.karol202.uranium.swing.nativeComponent
import pl.karol202.uranium.swing.util.*
import javax.swing.Icon
import javax.swing.JLabel

class SwingLabel(private val native: JLabel,
                 props: Props) : SwingAbstractComponent<SwingLabel.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 override val swingProps: SwingNativeComponent.Props = SwingNativeComponent.Props(),
	                 val text: Prop<String?> = Prop.NoValue,
	                 val icon: Prop<Icon?> = Prop.NoValue,
	                 val disabledIcon: Prop<Icon?> = Prop.NoValue,
	                 val iconTextGap: Prop<Int> = Prop.NoValue,
	                 val horizontalAlign: Prop<HorizontalAlign> = Prop.NoValue,
	                 val verticalAlign: Prop<VerticalAlign> = Prop.NoValue,
	                 val horizontalTextPosition: Prop<HorizontalAlign> = Prop.NoValue,
	                 val verticalTextPosition: Prop<VerticalAlign> = Prop.NoValue) : UProps,
	                                                                                 SwingNativeComponent.PropsProvider<Props>,
	                                                                                 PropsProvider<Props>
	{
		override val labelProps = this

		override fun withSwingProps(builder: Builder<SwingNativeComponent.Props>) =
				copy(swingProps = swingProps.builder())

		override fun withLabelProps(builder: Builder<Props>) = builder()
	}

	interface PropsProvider<S : PropsProvider<S>> : UProps
	{
		val labelProps: Props

		fun withLabelProps(builder: Builder<Props>): S
	}

	override fun SwingRenderBuilder.render()
	{
		+ nativeComponent(native = { native }, props = props.swingProps)
		onUpdate()
	}

	private fun onUpdate() = native.apply {
		props.text.ifPresent { text = it }
		props.icon.ifPresent { icon = it }
		props.disabledIcon.ifPresent { disabledIcon = it }
		props.iconTextGap.ifPresent { iconTextGap = it }
		props.horizontalAlign.ifPresent { horizontalAlignment = it.code }
		props.verticalAlign.ifPresent { verticalAlignment = it.code }
		props.horizontalTextPosition.ifPresent { horizontalTextPosition = it.code }
		props.verticalTextPosition.ifPresent { verticalTextPosition = it.code }
	}
}

fun SwingRenderBuilder.label(native: () -> JLabel = ::JLabel,
                             key: Any = AutoKey,
                             props: SwingLabel.Props = SwingLabel.Props(key)) =
		component({ SwingLabel(native(), it) }, props)

private typealias Provider<P> = SwingLabel.PropsProvider<P>
fun <P : Provider<P>> SwingElement<P>.withLabelProps(builder: Builder<SwingLabel.Props>) = withProps { withLabelProps(builder) }
fun <P : Provider<P>> SwingElement<P>.text(text: String?) = withLabelProps { copy(text = text.prop()) }
fun <P : Provider<P>> SwingElement<P>.icon(icon: Icon?) = withLabelProps { copy(icon = icon.prop()) }
fun <P : Provider<P>> SwingElement<P>.disabledIcon(icon: Icon?) = withLabelProps { copy(disabledIcon = icon.prop()) }
fun <P : Provider<P>> SwingElement<P>.iconTextGap(gap: Int) = withLabelProps { copy(iconTextGap = gap.prop()) }
fun <P : Provider<P>> SwingElement<P>.horizontalAlign(align: HorizontalAlign) = withLabelProps { copy(horizontalAlign = align.prop()) }
fun <P : Provider<P>> SwingElement<P>.verticalAlign(align: VerticalAlign) = withLabelProps { copy(verticalAlign = align.prop()) }
fun <P : Provider<P>> SwingElement<P>.horizontalTextPosition(position: HorizontalAlign) = withLabelProps { copy(horizontalTextPosition = position.prop()) }
fun <P : Provider<P>> SwingElement<P>.verticalTextPosition(position: VerticalAlign) = withLabelProps { copy(verticalTextPosition = position.prop()) }
