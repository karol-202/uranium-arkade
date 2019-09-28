package pl.karol202.uranium.swing.control.button

import pl.karol202.uranium.core.common.BaseProps
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.util.Prop
import pl.karol202.uranium.core.util.RenderBuilder
import pl.karol202.uranium.core.util.buildComponent
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
	                                                             SwingNativeComponent.Props.Provider<Props>
	{
		companion object
		{
			fun fromKey(key: Any) = Props(SwingNativeComponent.Props(BaseProps(key)))
		}

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

private typealias AbstractButtonBuilder = SwingComponentBuilder<SwingAbstractButton.Props>

fun SwingRenderBuilder.abstractButton(native: AbstractButton, props: SwingAbstractButton.Props) =
		buildComponent({ SwingAbstractButton(native, it) }, props)
fun AbstractButtonBuilder.text(text: String?) = withProps { copy(text = text.prop()) }
fun AbstractButtonBuilder.icon(icon: Icon?) = withProps { copy(icon = icon.prop()) }
fun AbstractButtonBuilder.pressedIcon(icon: Icon?) = withProps { copy(pressedIcon = icon.prop()) }
fun AbstractButtonBuilder.selectedIcon(icon: Icon?) = withProps { copy(selectedIcon = icon.prop()) }
fun AbstractButtonBuilder.rolloverIcon(icon: Icon?) = withProps { copy(rolloverIcon = icon.prop()) }
fun AbstractButtonBuilder.rolloverSelectedIcon(icon: Icon?) = withProps { copy(rolloverSelectedIcon = icon.prop()) }
fun AbstractButtonBuilder.disabledIcon(icon: Icon?) = withProps { copy(disabledIcon = icon.prop()) }
fun AbstractButtonBuilder.disabledSelectedIcon(icon: Icon?) = withProps { copy(disabledSelectedIcon = icon.prop()) }
fun AbstractButtonBuilder.iconTextGap(gap: Int) = withProps { copy(iconTextGap = gap.prop()) }
fun AbstractButtonBuilder.borderPainted(painted: Boolean) = withProps { copy(borderPainted = painted.prop()) }
fun AbstractButtonBuilder.contentAreaFilled(filled: Boolean) = withProps { copy(contentAreaFilled = filled.prop()) }
fun AbstractButtonBuilder.focusPainted(painted: Boolean) = withProps { copy(focusPainted = painted.prop()) }
fun AbstractButtonBuilder.rolloverEnabled(enabled: Boolean) = withProps { copy(rolloverEnabled = enabled.prop()) }
fun AbstractButtonBuilder.selected(selected: Boolean) = withProps { copy(selected = selected.prop()) }
fun AbstractButtonBuilder.horizontalAlign(align: HorizontalAlign) = withProps { copy(horizontalAlign = align.prop()) }
fun AbstractButtonBuilder.verticalAlign(align: VerticalAlign) = withProps { copy(verticalAlign = align.prop()) }
fun AbstractButtonBuilder.horizontalTextPosition(position: HorizontalAlign) = withProps { copy(horizontalTextPosition = position.prop()) }
fun AbstractButtonBuilder.verticalTextPosition(position: VerticalAlign) = withProps { copy(verticalTextPosition = position.prop()) }
fun AbstractButtonBuilder.margin(margin: Insets?) = withProps { copy(margin = margin.prop()) }
fun AbstractButtonBuilder.multiClickThreshold(threshold: Long) = withProps { copy(multiClickThreshold = threshold.prop()) }
fun AbstractButtonBuilder.onClick(onClick: () -> Unit) = withProps { copy(onClick = onClick.prop()) }
fun AbstractButtonBuilder.onSelect(onSelect: (Boolean) -> Unit) = withProps { copy(onSelect = onSelect.prop()) }
