package pl.karol202.uranium.swing.control

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.SwingBuilder
import java.awt.Label

class LabelSwingContext(props: Props) : SwingControl<LabelSwingContext.Props>(props)
{
	class Props(key: Any,
	            val text: String) : UProps(key)

	override val control = Label(props.text)

	override fun onUpdate()
	{
		control.text = props.text
	}
}

fun SwingBuilder.label(key: Any, text: String) = component(::LabelSwingContext, LabelSwingContext.Props(key, text))
