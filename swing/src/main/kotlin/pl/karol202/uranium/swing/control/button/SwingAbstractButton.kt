package pl.karol202.uranium.swing.control.button

import pl.karol202.uranium.core.util.Prop
import pl.karol202.uranium.swing.InvalidateableSwingContext
import pl.karol202.uranium.swing.SwingComponent
import pl.karol202.uranium.swing.control.SwingControl
import pl.karol202.uranium.swing.util.BaseListeners
import pl.karol202.uranium.swing.util.HorizontalAlign
import pl.karol202.uranium.swing.util.VerticalAlign
import java.awt.Insets
import java.awt.event.ActionListener
import java.awt.event.ItemEvent
import java.awt.event.ItemListener
import javax.swing.AbstractButton
import javax.swing.Icon

abstract class SwingAbstractButton<P : SwingAbstractButton.Props>(props: P) : SwingControl<P>(props)
{
	open class Props(override val key: Any,
	                 override val baseListeners: Prop<BaseListeners>,
	                 override val enabled: Prop<Boolean>,
	                 override val visible: Prop<Boolean>,
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
	                 val onSelect: ((Boolean) -> Unit)?) : SwingComponent.Props(key, baseListeners, enabled, visible)

	abstract override val native: AbstractButton

	private val actionListener = ActionListener { props.onClick?.invoke() }
	private val itemListener = ItemListener { props.onSelect?.invoke(it.stateChange == ItemEvent.SELECTED) }

	override fun onAttach(parentContext: InvalidateableSwingContext)
	{
		super.onAttach(parentContext)
		native.addActionListener(actionListener)
		native.addItemListener(itemListener)
	}

	override fun onUpdate()
	{
		super.onUpdate()
		native.text = props.text
		native.icon = props.icon
		native.pressedIcon = props.pressedIcon
		native.selectedIcon = props.selectedIcon
		native.rolloverIcon = props.rolloverIcon
		native.rolloverSelectedIcon = props.rolloverSelectedIcon
		native.disabledIcon = props.disabledIcon
		native.disabledSelectedIcon = props.disabledSelectedIcon
		native.iconTextGap = props.iconTextGap
		native.isBorderPainted = props.borderPainted
		native.isContentAreaFilled = props.contentAreaFilled
		native.isFocusPainted = props.focusPainted
		native.isRolloverEnabled = props.rolloverEnabled
		native.isSelected = props.selected
		native.horizontalAlignment = props.horizontalAlign.code
		native.horizontalTextPosition = props.horizontalTextPosition.code
		native.verticalAlignment = props.verticalAlign.code
		native.verticalTextPosition = props.verticalTextPosition.code
		native.margin = props.margin
		native.multiClickThreshhold = props.multiClickThreshold
	}

	override fun onDetach(parentContext: InvalidateableSwingContext)
	{
		super.onDetach(parentContext)
		native.removeActionListener(actionListener)
		native.removeItemListener(itemListener)
	}
}
