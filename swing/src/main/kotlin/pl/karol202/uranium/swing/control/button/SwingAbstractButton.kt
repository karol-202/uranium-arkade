package pl.karol202.uranium.swing.control.button

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
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
	                                                             SwingNativeComponent.PropsProvider<Props>
	{
		override fun withSwingProps(builder: SwingNativeComponent.Props.() -> SwingNativeComponent.Props) =
				copy(swingProps = swingProps.builder())
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

private typealias AbstractButtonElement = SwingElement<SwingAbstractButton.Props>

fun SwingRenderBuilder.abstractButton(native: AbstractButton, props: SwingAbstractButton.Props) =
		component({ SwingAbstractButton(native, it) }, props)
fun AbstractButtonElement.text(text: String?) = withProps { copy(text = text.prop()) }
fun AbstractButtonElement.icon(icon: Icon?) = withProps { copy(icon = icon.prop()) }
fun AbstractButtonElement.pressedIcon(icon: Icon?) = withProps { copy(pressedIcon = icon.prop()) }
fun AbstractButtonElement.selectedIcon(icon: Icon?) = withProps { copy(selectedIcon = icon.prop()) }
fun AbstractButtonElement.rolloverIcon(icon: Icon?) = withProps { copy(rolloverIcon = icon.prop()) }
fun AbstractButtonElement.rolloverSelectedIcon(icon: Icon?) = withProps { copy(rolloverSelectedIcon = icon.prop()) }
fun AbstractButtonElement.disabledIcon(icon: Icon?) = withProps { copy(disabledIcon = icon.prop()) }
fun AbstractButtonElement.disabledSelectedIcon(icon: Icon?) = withProps { copy(disabledSelectedIcon = icon.prop()) }
fun AbstractButtonElement.iconTextGap(gap: Int) = withProps { copy(iconTextGap = gap.prop()) }
fun AbstractButtonElement.borderPainted(painted: Boolean) = withProps { copy(borderPainted = painted.prop()) }
fun AbstractButtonElement.contentAreaFilled(filled: Boolean) = withProps { copy(contentAreaFilled = filled.prop()) }
fun AbstractButtonElement.focusPainted(painted: Boolean) = withProps { copy(focusPainted = painted.prop()) }
fun AbstractButtonElement.rolloverEnabled(enabled: Boolean) = withProps { copy(rolloverEnabled = enabled.prop()) }
fun AbstractButtonElement.selected(selected: Boolean) = withProps { copy(selected = selected.prop()) }
fun AbstractButtonElement.horizontalAlign(align: HorizontalAlign) = withProps { copy(horizontalAlign = align.prop()) }
fun AbstractButtonElement.verticalAlign(align: VerticalAlign) = withProps { copy(verticalAlign = align.prop()) }
fun AbstractButtonElement.horizontalTextPosition(position: HorizontalAlign) = withProps { copy(horizontalTextPosition = position.prop()) }
fun AbstractButtonElement.verticalTextPosition(position: VerticalAlign) = withProps { copy(verticalTextPosition = position.prop()) }
fun AbstractButtonElement.margin(margin: Insets?) = withProps { copy(margin = margin.prop()) }
fun AbstractButtonElement.multiClickThreshold(threshold: Long) = withProps { copy(multiClickThreshold = threshold.prop()) }
fun AbstractButtonElement.onClick(onClick: () -> Unit) = withProps { copy(onClick = onClick.prop()) }
fun AbstractButtonElement.onSelect(onSelect: (Boolean) -> Unit) = withProps { copy(onSelect = onSelect.prop()) }
