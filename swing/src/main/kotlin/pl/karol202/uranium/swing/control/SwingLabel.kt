package pl.karol202.uranium.swing.control

import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.SwingBuilder
import pl.karol202.uranium.swing.SwingComponent
import pl.karol202.uranium.swing.util.BaseListeners
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
	            baseListeners: BaseListeners,
	            enabled: Boolean,
	            visible: Boolean,
	            val text: String?,
	            val icon: Icon?,
	            val disabledIcon: Icon?,
	            val iconTextGap: Int,
	            val horizontalAlign: HorizontalAlign,
	            val verticalAlign: VerticalAlign,
	            val horizontalTextPosition: HorizontalAlign,
	            val verticalTextPosition: VerticalAlign) : SwingComponent.Props(key, baseListeners, enabled, visible)

	override val native = JLabel()

	override fun onUpdate()
	{
		super.onUpdate()
		native.text = props.text
		native.icon = props.icon
		native.disabledIcon = props.disabledIcon
		native.iconTextGap = props.iconTextGap
		native.horizontalAlignment = props.horizontalAlign.code
		native.verticalAlignment = props.verticalAlign.code
		native.horizontalTextPosition = props.horizontalTextPosition.code
		native.verticalTextPosition = props.verticalTextPosition.code
	}
}

fun SwingBuilder.label(key: Any,
                       baseListeners: BaseListeners = BaseListeners(),
                       enabled: Boolean = true,
                       visible: Boolean = true,
                       text: String? = null,
                       icon: Icon? = null,
                       disabledIcon: Icon? = null,
                       iconTextGap: Int = 4,
                       horizontalAlign: LabelSwingContext.HorizontalAlign = LabelSwingContext.HorizontalAlign.LEADING,
                       verticalAlign: LabelSwingContext.VerticalAlign = LabelSwingContext.VerticalAlign.CENTER,
                       horizontalTextPosition: LabelSwingContext.HorizontalAlign = LabelSwingContext.HorizontalAlign.TRAILING,
                       verticalTextPosition: LabelSwingContext.VerticalAlign = LabelSwingContext.VerticalAlign.CENTER) =
		component(::LabelSwingContext,
		          LabelSwingContext.Props(key, baseListeners, enabled, visible, text, icon, disabledIcon, iconTextGap,
		                                  horizontalAlign, verticalAlign, horizontalTextPosition, verticalTextPosition))
