package pl.karol202.uranium.swing.control

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.SwingBuilder
import java.awt.Button

class SwingButton(props: Props) : SwingControl<SwingButton.Props>(props)
{
	class Props(key: Any,
	            val text: String) : UProps(key)

	override val control = Button(props.text)

	override fun onUpdate()
	{
		control.label = props.text
	}
}

fun SwingBuilder.button(key: Any, text: String) = component(::SwingButton, SwingButton.Props(key, text))
