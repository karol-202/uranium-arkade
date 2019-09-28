package pl.karol202.uranium.swing.control.button

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.util.Prop
import pl.karol202.uranium.core.util.RenderBuilder
import pl.karol202.uranium.core.util.buildComponent
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
	                 val horizontalTextPosition: Prop<HorizontalAlign>,
	                 val verticalAlign: Prop<VerticalAlign>,
	                 val verticalTextPosition: Prop<VerticalAlign>,
	                 val margin: Prop<Insets?>,
	                 val multiClickThreshold: Prop<Long>,
	                 val onClick: Prop<(() -> Unit)?>,
	                 val onSelect: Prop<((Boolean) -> Unit)?>) : UProps by swingProps,
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
		+ nativeComponent(native = native)
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
		props.horizontalTextPosition.ifPresent { native.horizontalTextPosition = it.code }
		props.verticalAlign.ifPresent { native.verticalAlignment = it.code }
		props.verticalTextPosition.ifPresent { native.verticalTextPosition = it.code }
		props.margin.ifPresent { native.margin = it }
		props.multiClickThreshold.ifPresent { native.multiClickThreshhold = it }
	}
}

fun SwingRenderBuilder.abstractButton(native: AbstractButton, props: SwingAbstractButton.Props) =
		buildComponent({ SwingAbstractButton(it, native) }, props)
