package pl.karol202.uranium.swing.control.button

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

class SwingAbstractButton(props: Props,
                          private val native: AbstractButton) : SwingAbstractComponent<SwingAbstractButton.Props>(props)
{
	data class Props(override val swingProps: SwingNativeComponent.Props,
	                 val text: Prop<String?>,
	                 val icon: Prop<Icon?>,
	                 val pressedIcon: Prop<Icon?>,
	                 val selectedIcon: Prop<Icon?>,
	                 val rolloverIcon: Prop<Icon?>,
	                 val rolloverSelectedIcon: Prop<Icon?>,
	                 val disabledIcon: Prop<Icon?>,
	                 val disabledSelectedIcon: Prop<Icon?>,
	                 val iconTextGap: Prop<Int>,
	                 val borderPainted: Prop<Boolean>,
	                 val contentAreaFilled: Prop<Boolean>,
	                 val focusPainted: Prop<Boolean>,
	                 val rolloverEnabled: Prop<Boolean>,
	                 val selected: Prop<Boolean>,
	                 val horizontalAlign: Prop<HorizontalAlign>,
	                 val verticalAlign: Prop<VerticalAlign>,
	                 val horizontalTextPosition: Prop<HorizontalAlign>,
	                 val verticalTextPosition: Prop<VerticalAlign>,
	                 val margin: Prop<Insets?>,
	                 val multiClickThreshold: Prop<Long>,
	                 val onClick: Prop<() -> Unit>,
	                 val onSelect: Prop<(Boolean) -> Unit>) : UProps by swingProps,
	                                                             SwingNativeComponent.Props.Provider<Props>
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

	private fun onUpdate()
	{
		props.text.ifPresent { native.text = it }
		props.icon.ifPresent { native.icon = it }
		props.pressedIcon.ifPresent { native.pressedIcon = it }
		props.selectedIcon.ifPresent { native.selectedIcon = it }
		props.rolloverIcon.ifPresent { native.rolloverIcon = it }
		props.rolloverSelectedIcon.ifPresent { native.rolloverSelectedIcon = it }
		props.disabledIcon.ifPresent { native.disabledIcon = it }
		props.disabledSelectedIcon.ifPresent { native.disabledSelectedIcon = it }
		props.iconTextGap.ifPresent { native.iconTextGap = it }
		props.borderPainted.ifPresent { native.isBorderPainted = it }
		props.contentAreaFilled.ifPresent { native.isContentAreaFilled = it }
		props.focusPainted.ifPresent { native.isFocusPainted = it }
		props.rolloverEnabled.ifPresent { native.isRolloverEnabled = it }
		props.selected.ifPresent { native.isSelected = it }
		props.horizontalAlign.ifPresent { native.horizontalAlignment = it.code }
		props.verticalAlign.ifPresent { native.verticalAlignment = it.code }
		props.horizontalTextPosition.ifPresent { native.horizontalTextPosition = it.code }
		props.verticalTextPosition.ifPresent { native.verticalTextPosition = it.code }
		props.margin.ifPresent { native.margin = it }
		props.multiClickThreshold.ifPresent { native.multiClickThreshhold = it }
	}
}

private typealias AbstractButtonBuilder = SwingComponentBuilder<SwingAbstractButton.Props>

fun SwingRenderBuilder.abstractButton(native: AbstractButton, props: SwingAbstractButton.Props) =
		buildComponent({ SwingAbstractButton(it, native) }, props)
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
