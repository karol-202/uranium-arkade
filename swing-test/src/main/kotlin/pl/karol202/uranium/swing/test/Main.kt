package pl.karol202.uranium.swing.test

import pl.karol202.uranium.core.common.BaseProps
import pl.karol202.uranium.core.common.UState
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.SwingRenderBuilder
import pl.karol202.uranium.swing.SwingStatefulComponent
import pl.karol202.uranium.swing.control.button.*
import pl.karol202.uranium.swing.control.label
import pl.karol202.uranium.swing.control.text
import pl.karol202.uranium.swing.control.text.text
import pl.karol202.uranium.swing.control.text.textField
import pl.karol202.uranium.swing.frame.SwingFrame
import pl.karol202.uranium.swing.layout.flowLayout
import javax.swing.UIManager

fun main()
{
	UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")
	SwingFrame.withRoot { + counter(0) }.withTitle("Uranium test").withSize(640, 480).show()
}

class CounterComponent(props: BaseProps) : SwingStatefulComponent<BaseProps, CounterComponent.State>(props, State())
{
	data class State(val text: String = "start",
	                 val checked: Boolean = false) : UState

	override fun SwingRenderBuilder.render()
	{
		+ flowLayout(key = 0) {
			+ label(key = 0).text("Tekst: ${state.text} Stan: ${state.checked}")
			+ textField(key = 1).text(state.text)
			+ checkBox(key = 2).text("Checkbox").selected(state.checked).onSelect { setChecked(it) }
			+ radioButton(key = 3).text("Radio").selected(state.checked).onSelect { setChecked(it) }
		}
	}

	//private fun increment() = setState(state.copy(counter = state.counter + 1))

	private fun setChecked(checked: Boolean) = setState(state.copy(checked = checked))
}

fun SwingRenderBuilder.counter(key: Any) = component(::CounterComponent, BaseProps(key))
