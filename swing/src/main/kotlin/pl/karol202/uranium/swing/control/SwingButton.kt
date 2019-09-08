package pl.karol202.uranium.swing.control

import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.SwingBuilder
import pl.karol202.uranium.swing.SwingComponent
import pl.karol202.uranium.swing.util.BaseListeners
import javax.swing.JButton

class SwingButton(props: Props) : SwingControl<SwingButton.Props>(props)
{
	class Props(key: Any,
	            baseListeners: BaseListeners,
	            enabled: Boolean,
	            visible: Boolean,
	            val text: String?) : SwingComponent.Props(key, baseListeners, enabled, visible)

	override val native = JButton()

	override fun onUpdate()
	{
		super.onUpdate()
		native.text = props.text
	}
}

fun SwingBuilder.button(key: Any,
                        baseListeners: BaseListeners = BaseListeners(),
                        enabled: Boolean = true,
                        visible: Boolean = true,
                        text: String? = null) =
		component(::SwingButton, SwingButton.Props(key, baseListeners, enabled, visible, text))
