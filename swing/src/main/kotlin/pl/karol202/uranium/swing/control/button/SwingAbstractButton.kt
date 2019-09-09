package pl.karol202.uranium.swing.control.button

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
	open class Props(key: Any,
	                 baseListeners: BaseListeners?,
	                 enabled: Boolean,
	                 visible: Boolean,
	                 val text: String?,
	                 val icon: Icon?,
	                 val pressedIcon: Icon?,
	                 val selectedIcon: Icon?,
	                 val rolloverIcon: Icon?,
	                 val rolloverSelectedIcon: Icon?,
	                 val disabledIcon: Icon?,
	                 val disabledSelectedIcon: Icon?,
	                 val iconTextGap: Int,
	                 val borderPainted: Boolean,
	                 val contentAreaFilled: Boolean,
	                 val focusPainted: Boolean,
	                 val rolloverEnabled: Boolean,
	                 val selected: Boolean,
	                 val horizontalAlign: HorizontalAlign,
	                 val horizontalTextPosition: HorizontalAlign,
	                 val verticalAlign: VerticalAlign,
	                 val verticalTextPosition: VerticalAlign,
	                 val margin: Insets?,
	                 val multiClickThreshold: Long,
	                 val onClick: (() -> Unit)?,
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
