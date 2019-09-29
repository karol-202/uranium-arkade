package pl.karol202.uranium.swing.control.button

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.core.util.Builder
import pl.karol202.uranium.core.util.Prop
import pl.karol202.uranium.core.util.RenderBuilder
import pl.karol202.uranium.core.util.prop
import pl.karol202.uranium.swing.*
import pl.karol202.uranium.swing.util.HorizontalAlign
import pl.karol202.uranium.swing.util.VerticalAlign
import java.awt.Insets
import java.awt.event.ActionListener
import java.awt.event.ItemEvent
import java.awt.event.ItemListener
import javax.swing.AbstractButton
import javax.swing.Icon

class SwingAbstractButton(private val native: AbstractButton,
                          props: Props) : SwingAbstractComponent<SwingAbstractButton.Props>(props)
{
	companion object
	{
		fun props(key: Any) = Props(SwingNativeComponent.props(key))
	}

	data class Props(override val swingProps: SwingNativeComponent.Props,
	                 val text: Prop<String?> = Prop.NoValue,
	                 val icon: Prop<Icon?> = Prop.NoValue,
	                 val pressedIcon: Prop<Icon?> = Prop.NoValue,
	                 val selectedIcon: Prop<Icon?> = Prop.NoValue,
	                 val rolloverIcon: Prop<Icon?> = Prop.NoValue,
	                 val rolloverSelectedIcon: Prop<Icon?> = Prop.NoValue,
	                 val disabledIcon: Prop<Icon?> = Prop.NoValue,
	                 val disabledSelectedIcon: Prop<Icon?> = Prop.NoValue,
	                 val iconTextGap: Prop<Int> = Prop.NoValue,
	                 val borderPainted: Prop<Boolean> = Prop.NoValue,
	                 val contentAreaFilled: Prop<Boolean> = Prop.NoValue,
	                 val focusPainted: Prop<Boolean> = Prop.NoValue,
	                 val rolloverEnabled: Prop<Boolean> = Prop.NoValue,
	                 val selected: Prop<Boolean> = Prop.NoValue,
	                 val horizontalAlign: Prop<HorizontalAlign> = Prop.NoValue,
	                 val verticalAlign: Prop<VerticalAlign> = Prop.NoValue,
	                 val horizontalTextPosition: Prop<HorizontalAlign> = Prop.NoValue,
	                 val verticalTextPosition: Prop<VerticalAlign> = Prop.NoValue,
	                 val margin: Prop<Insets?> = Prop.NoValue,
	                 val multiClickThreshold: Prop<Long> = Prop.NoValue,
	                 val onClick: Prop<() -> Unit> = Prop.NoValue,
	                 val onSelect: Prop<(Boolean) -> Unit> = Prop.NoValue) : UProps by swingProps,
	                                                                         SwingNativeComponent.PropsProvider<Props>,
                                                                             PropsProvider<Props>
	{
		override val abstractButtonProps = this

		override fun withSwingProps(builder: Builder<SwingNativeComponent.Props>) = copy(swingProps = swingProps.builder())

		override fun withAbstractButtonProps(builder: Builder<Props>) = builder()
	}

	interface PropsProvider<S : PropsProvider<S>> : UProps
	{
		val abstractButtonProps: Props

		fun withAbstractButtonProps(builder: Builder<Props>): S
	}

	private val actionListener = ActionListener { props.onClick.value?.invoke() }
	private val itemListener = ItemListener { props.onSelect.value?.invoke(it.stateChange == ItemEvent.SELECTED) }

	override fun onAttach(parentContext: InvalidateableSwingContext)
	{
		super.onAttach(parentContext)
		native.addActionListener(actionListener)
		native.addItemListener(itemListener)
	}

	override fun onDetach(parentContext: InvalidateableSwingContext)
	{
		super.onDetach(parentContext)
		native.removeActionListener(actionListener)
		native.removeItemListener(itemListener)
	}

	override fun RenderBuilder<SwingNative>.render()
	{
		+ nativeComponent(native = native, props = props.swingProps)
		onUpdate()
	}

	private fun onUpdate() = native.apply {
		props.text.ifPresent { text = it }
		props.icon.ifPresent { icon = it }
		props.pressedIcon.ifPresent { pressedIcon = it }
		props.selectedIcon.ifPresent { selectedIcon = it }
		props.rolloverIcon.ifPresent { rolloverIcon = it }
		props.rolloverSelectedIcon.ifPresent { rolloverSelectedIcon = it }
		props.disabledIcon.ifPresent { disabledIcon = it }
		props.disabledSelectedIcon.ifPresent { disabledSelectedIcon = it }
		props.iconTextGap.ifPresent { iconTextGap = it }
		props.borderPainted.ifPresent { isBorderPainted = it }
		props.contentAreaFilled.ifPresent { isContentAreaFilled = it }
		props.focusPainted.ifPresent { isFocusPainted = it }
		props.rolloverEnabled.ifPresent { isRolloverEnabled = it }
		props.selected.ifPresent { isSelected = it }
		props.horizontalAlign.ifPresent { horizontalAlignment = it.code }
		props.verticalAlign.ifPresent { verticalAlignment = it.code }
		props.horizontalTextPosition.ifPresent { horizontalTextPosition = it.code }
		props.verticalTextPosition.ifPresent { verticalTextPosition = it.code }
		props.margin.ifPresent { margin = it }
		props.multiClickThreshold.ifPresent { multiClickThreshhold = it }
	}
}

private typealias Provider<P> = SwingAbstractButton.PropsProvider<P>
fun <P : Provider<P>> SwingElement<P>.withAbstractButtonProps(builder: Builder<SwingAbstractButton.Props>) =
		withProps { withAbstractButtonProps(builder) }

fun SwingRenderBuilder.abstractButton(native: AbstractButton, props: SwingAbstractButton.Props) =
		component({ SwingAbstractButton(native, it) }, props)
fun <P : Provider<P>> SwingElement<P>.text(text: String?) = withAbstractButtonProps { copy(text = text.prop()) }
fun <P : Provider<P>> SwingElement<P>.icon(icon: Icon?) = withAbstractButtonProps { copy(icon = icon.prop()) }
fun <P : Provider<P>> SwingElement<P>.pressedIcon(icon: Icon?) = withAbstractButtonProps { copy(pressedIcon = icon.prop()) }
fun <P : Provider<P>> SwingElement<P>.selectedIcon(icon: Icon?) = withAbstractButtonProps { copy(selectedIcon = icon.prop()) }
fun <P : Provider<P>> SwingElement<P>.rolloverIcon(icon: Icon?) = withAbstractButtonProps { copy(rolloverIcon = icon.prop()) }
fun <P : Provider<P>> SwingElement<P>.rolloverSelectedIcon(icon: Icon?) = withAbstractButtonProps { copy(rolloverSelectedIcon = icon.prop()) }
fun <P : Provider<P>> SwingElement<P>.disabledIcon(icon: Icon?) = withAbstractButtonProps { copy(disabledIcon = icon.prop()) }
fun <P : Provider<P>> SwingElement<P>.disabledSelectedIcon(icon: Icon?) = withAbstractButtonProps { copy(disabledSelectedIcon = icon.prop()) }
fun <P : Provider<P>> SwingElement<P>.iconTextGap(gap: Int) = withAbstractButtonProps { copy(iconTextGap = gap.prop()) }
fun <P : Provider<P>> SwingElement<P>.borderPainted(painted: Boolean) = withAbstractButtonProps { copy(borderPainted = painted.prop()) }
fun <P : Provider<P>> SwingElement<P>.contentAreaFilled(filled: Boolean) = withAbstractButtonProps { copy(contentAreaFilled = filled.prop()) }
fun <P : Provider<P>> SwingElement<P>.focusPainted(painted: Boolean) = withAbstractButtonProps { copy(focusPainted = painted.prop()) }
fun <P : Provider<P>> SwingElement<P>.rolloverEnabled(enabled: Boolean) = withAbstractButtonProps { copy(rolloverEnabled = enabled.prop()) }
fun <P : Provider<P>> SwingElement<P>.selected(selected: Boolean) = withAbstractButtonProps { copy(selected = selected.prop()) }
fun <P : Provider<P>> SwingElement<P>.horizontalAlign(align: HorizontalAlign) = withAbstractButtonProps { copy(horizontalAlign = align.prop()) }
fun <P : Provider<P>> SwingElement<P>.verticalAlign(align: VerticalAlign) = withAbstractButtonProps { copy(verticalAlign = align.prop()) }
fun <P : Provider<P>> SwingElement<P>.horizontalTextPosition(position: HorizontalAlign) = withAbstractButtonProps { copy(horizontalTextPosition = position.prop()) }
fun <P : Provider<P>> SwingElement<P>.verticalTextPosition(position: VerticalAlign) = withAbstractButtonProps { copy(verticalTextPosition = position.prop()) }
fun <P : Provider<P>> SwingElement<P>.margin(margin: Insets?) = withAbstractButtonProps { copy(margin = margin.prop()) }
fun <P : Provider<P>> SwingElement<P>.multiClickThreshold(threshold: Long) = withAbstractButtonProps { copy(multiClickThreshold = threshold.prop()) }
fun <P : Provider<P>> SwingElement<P>.onClick(onClick: () -> Unit) = withAbstractButtonProps { copy(onClick = onClick.prop()) }
fun <P : Provider<P>> SwingElement<P>.onSelect(onSelect: (Boolean) -> Unit) = withAbstractButtonProps { copy(onSelect = onSelect.prop()) }
