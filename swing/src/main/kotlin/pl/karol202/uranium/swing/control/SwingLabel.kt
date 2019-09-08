package pl.karol202.uranium.swing.control

import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.SwingBuilder
import pl.karol202.uranium.swing.SwingComponent
import pl.karol202.uranium.swing.util.BaseListeners
import pl.karol202.uranium.swing.util.HorizontalAlign
import pl.karol202.uranium.swing.util.VerticalAlign
import javax.swing.Icon
import javax.swing.JLabel

class LabelSwingContext(props: Props) : SwingControl<LabelSwingContext.Props>(props)
{
	class Props(key: Any,
	            baseListeners: BaseListeners?,
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
                       baseListeners: BaseListeners? = null,
                       enabled: Boolean = true,
                       visible: Boolean = true,
                       text: String? = null,
                       icon: Icon? = null,
                       disabledIcon: Icon? = null,
                       iconTextGap: Int = 4,
                       horizontalAlign: HorizontalAlign = HorizontalAlign.LEADING,
                       verticalAlign: VerticalAlign = VerticalAlign.CENTER,
                       horizontalTextPosition: HorizontalAlign = HorizontalAlign.TRAILING,
                       verticalTextPosition: VerticalAlign = VerticalAlign.CENTER) =
		component(::LabelSwingContext,
		          LabelSwingContext.Props(key, baseListeners, enabled, visible, text, icon, disabledIcon, iconTextGap,
		                                  horizontalAlign, verticalAlign, horizontalTextPosition, verticalTextPosition))
