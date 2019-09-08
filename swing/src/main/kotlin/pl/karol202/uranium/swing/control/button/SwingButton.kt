package pl.karol202.uranium.swing.control.button

import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.SwingBuilder
import pl.karol202.uranium.swing.util.BaseListeners
import pl.karol202.uranium.swing.util.HorizontalAlign
import pl.karol202.uranium.swing.util.VerticalAlign
import java.awt.Insets
import java.awt.event.ActionEvent
import javax.swing.Icon
import javax.swing.JButton

class SwingButton(props: Props) : SwingAbstractButton<SwingButton.Props>(props)
{
	class Props(key: Any,
	            baseListeners: BaseListeners?,
	            enabled: Boolean,
	            visible: Boolean,
	            text: String?,
	            icon: Icon?,
	            pressedIcon: Icon?,
	            selectedIcon: Icon?,
	            rolloverIcon: Icon?,
	            rolloverSelectedIcon: Icon?,
	            disabledIcon: Icon?,
	            disabledSelectedIcon: Icon?,
	            iconTextGap: Int,
	            borderPainted: Boolean,
	            contentAreaFilled: Boolean,
	            focusPainted: Boolean,
	            rolloverEnabled: Boolean,
	            horizontalAlign: HorizontalAlign,
	            horizontalTextPosition: HorizontalAlign,
	            verticalAlign: VerticalAlign,
	            verticalTextPosition: VerticalAlign,
	            margin: Insets?,
	            multiClickThreshold: Long,
	            onAction: ((ActionEvent) -> Unit)?) :
			SwingAbstractButton.Props(key, baseListeners, enabled, visible, text, icon, pressedIcon, selectedIcon,
			                          rolloverIcon, rolloverSelectedIcon, disabledIcon, disabledSelectedIcon, iconTextGap,
			                          borderPainted, contentAreaFilled, focusPainted, rolloverEnabled, horizontalAlign,
			                          horizontalTextPosition, verticalAlign, verticalTextPosition, margin,
			                          multiClickThreshold, onAction)

	override val native = JButton()
}

fun SwingBuilder.button(key: Any,
                        baseListeners: BaseListeners? = null,
                        enabled: Boolean = true,
                        visible: Boolean = true,
                        text: String? = null,
                        icon: Icon? = null,
                        pressedIcon: Icon? = null,
                        selectedIcon: Icon? = null,
                        rolloverIcon: Icon? = null,
                        rolloverSelectedIcon: Icon? = null,
                        disabledIcon: Icon? = null,
                        disabledSelectedIcon: Icon? = null,
                        iconTextGap: Int = 4,
                        borderPainted: Boolean = true,
                        contentAreaFilled: Boolean = true,
                        focusPainted: Boolean = true,
                        rolloverEnabled: Boolean = false,
                        horizontalAlign: HorizontalAlign = HorizontalAlign.CENTER,
                        horizontalTextPosition: HorizontalAlign = HorizontalAlign.TRAILING,
                        verticalAlign: VerticalAlign = VerticalAlign.CENTER,
                        verticalTextPosition: VerticalAlign = VerticalAlign.CENTER,
                        margin: Insets? = null,
                        multiClickThreshold: Long = 0,
                        onAction: ((ActionEvent) -> Unit)? = null) =
		component(::SwingButton,
		          SwingButton.Props(key, baseListeners, enabled, visible, text, icon, pressedIcon, selectedIcon,
		                            rolloverIcon, rolloverSelectedIcon, disabledIcon, disabledSelectedIcon, iconTextGap,
		                            borderPainted, contentAreaFilled, focusPainted, rolloverEnabled, horizontalAlign,
		                            horizontalTextPosition, verticalAlign, verticalTextPosition, margin, multiClickThreshold,
		                            onAction))
