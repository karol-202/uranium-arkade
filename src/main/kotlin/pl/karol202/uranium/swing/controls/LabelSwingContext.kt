package pl.karol202.uranium.swing.controls

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.core.context.InvalidateableContext
import pl.karol202.uranium.swing.ControlSwingComponent
import pl.karol202.uranium.swing.SwingBuilder
import pl.karol202.uranium.swing.SwingNative
import java.awt.Label

class LabelSwingContext(props: Props) : ControlSwingComponent<LabelSwingContext.Props>(props)
{
	class Props(key: Any,
	            val text: String) : UProps(key)

	private val label = Label(props.text)

	override fun onAttach(parentContext: InvalidateableContext<SwingNative>)
	{
		parentContext.attachNative(label)
	}

	override fun onUpdate()
	{
		label.text = props.text
	}

	override fun onDetach(parentContext: InvalidateableContext<SwingNative>)
	{
		parentContext.detachNative(label)
	}
}

fun SwingBuilder.label(key: Any, text: String) = component(::LabelSwingContext, LabelSwingContext.Props(key, text))
