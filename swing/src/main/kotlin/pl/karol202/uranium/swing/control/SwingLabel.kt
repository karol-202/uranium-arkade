package pl.karol202.uranium.swing.control

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.SwingBuilder
import javax.swing.Icon
import javax.swing.JLabel
import javax.swing.SwingConstants

class LabelSwingContext(props: Props) : SwingControl<LabelSwingContext.Props>(props)
{
	enum class HorizontalAlign(val code: Int)
	{
		LEFT(SwingConstants.LEFT),
		CENTER(SwingConstants.CENTER),
		RIGHT(SwingConstants.RIGHT),
		LEADING(SwingConstants.LEADING),
		TRAILING(SwingConstants.TRAILING)
	}

	enum class VerticalAlign(val code: Int)
	{
		TOP(SwingConstants.TOP),
		CENTER(SwingConstants.CENTER),
		BOTTOM(SwingConstants.BOTTOM)
	}

	class Props(key: Any,
	            val text: String?,
	            val icon: Icon?,
	            val disabledIcon: Icon?,
	            val iconTextGap: Int,
	            val horizontalAlign: HorizontalAlign,
	            val verticalAlign: VerticalAlign,
	            val horizontalTextPosition: HorizontalAlign,
	            val verticalTextPosition: VerticalAlign) : UProps(key)

	override val control = JLabel()

	override fun onUpdate()
	{
		control.text = props.text
		control.icon = props.icon
		control.disabledIcon = props.disabledIcon
		control.iconTextGap = props.iconTextGap
		control.horizontalAlignment = props.horizontalAlign.code
		control.verticalAlignment = props.verticalAlign.code
		control.horizontalTextPosition = props.horizontalTextPosition.code
		control.verticalTextPosition = props.verticalTextPosition.code
	}
}

fun SwingBuilder.label(key: Any,
                       text: String? = null,
                       icon: Icon? = null,
                       disabledIcon: Icon? = null,
                       iconTextGap: Int = 4,
                       horizontalAlign: LabelSwingContext.HorizontalAlign = LabelSwingContext.HorizontalAlign.LEADING,
                       verticalAlign: LabelSwingContext.VerticalAlign = LabelSwingContext.VerticalAlign.CENTER,
                       horizontalTextPosition: LabelSwingContext.HorizontalAlign = LabelSwingContext.HorizontalAlign.TRAILING,
                       verticalTextPosition: LabelSwingContext.VerticalAlign = LabelSwingContext.VerticalAlign.CENTER) =
		component(::LabelSwingContext,
		          LabelSwingContext.Props(key, text, icon, disabledIcon, iconTextGap,
		                                  horizontalAlign, verticalAlign, horizontalTextPosition, verticalTextPosition))
