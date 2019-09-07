package pl.karol202.uranium.swing.controls

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.ControlSwingComponent
import pl.karol202.uranium.swing.InvalidateableSwingContext
import pl.karol202.uranium.swing.SwingBuilder
import java.awt.Button

class ButtonSwingComponent(props: Props) : ControlSwingComponent<ButtonSwingComponent.Props>(props)
{
	class Props(key: Any,
	            val text: String) : UProps(key)

	private val button = Button(props.text)

	override fun onAttach(parentContext: InvalidateableSwingContext)
	{
		parentContext.attachNative(button)
	}

	override fun onUpdate()
	{
		button.label = props.text
	}

	override fun onDetach(parentContext: InvalidateableSwingContext)
	{
		parentContext.detachNative(button)
	}
}

fun SwingBuilder.button(key: Any, text: String) = component(::ButtonSwingComponent, ButtonSwingComponent.Props(key, text))
