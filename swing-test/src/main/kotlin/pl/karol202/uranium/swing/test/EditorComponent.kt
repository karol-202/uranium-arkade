package pl.karol202.uranium.swing.test

import pl.karol202.uranium.core.common.*
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.swing.control.text.onApply
import pl.karol202.uranium.swing.control.text.onTextChange
import pl.karol202.uranium.swing.control.text.text
import pl.karol202.uranium.swing.control.text.textField
import pl.karol202.uranium.swing.test.EditorComponent.Props
import pl.karol202.uranium.swing.test.EditorComponent.State
import pl.karol202.uranium.swing.SwingRenderScope
import pl.karol202.uranium.swing.component.SwingAbstractAppComponent

class EditorComponent(props: Props) : SwingAbstractAppComponent<Props>(props),
                                      UStateful<State>
{
	data class Props(override val key: Any,
	                 val initialValue: String?,
	                 val onApply: (String) -> Unit): UProps

	data class State(val value: String) : UState

	override var state by state(State(props.initialValue ?: ""))

	override fun SwingRenderScope.render() =
			textField().text(state.value).onTextChange { setValue(it) }.onApply { onApply() }

	private fun setValue(value: String) = setState { copy(value = value) }

	private fun onApply() = props.onApply(state.value)

	override fun onUpdate(previousProps: Props?)
	{
		if(props.initialValue != previousProps?.initialValue) setValue(props.initialValue ?: "")
	}
}

fun SwingRenderScope.editorComponent(key: Any = AutoKey,
                                                                                  initialValue: String?,
                                                                                  onApply: (String) -> Unit) =
		component(::EditorComponent, Props(key, initialValue, onApply))
