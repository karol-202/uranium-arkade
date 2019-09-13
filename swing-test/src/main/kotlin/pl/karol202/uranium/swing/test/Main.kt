package pl.karol202.uranium.swing.test

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.common.UState
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.SwingRenderBuilder
import pl.karol202.uranium.swing.SwingStatefulComponent
import pl.karol202.uranium.swing.baseListeners
import pl.karol202.uranium.swing.control.button.button
import pl.karol202.uranium.swing.control.button.checkBox
import pl.karol202.uranium.swing.control.button.radioButton
import pl.karol202.uranium.swing.control.label
import pl.karol202.uranium.swing.control.text
import pl.karol202.uranium.swing.frame.SwingFrame
import pl.karol202.uranium.swing.layout.flowLayout
import pl.karol202.uranium.swing.util.BaseListeners
import javax.swing.UIManager

fun main()
{
	UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")
	SwingFrame.withRoot { counter(0) }.withTitle("Uranium test").withSize(640, 480).show()
}

class CounterComponent(props: UProps) : SwingStatefulComponent<UProps, CounterComponent.State>(props, State())
{
	data class State(val counter: Int = 0,
	                 val checked: Boolean = false) : UState

	override fun SwingRenderBuilder.render()
	{
		flowLayout(key = 0) {
			+label(key = 0)
					.text("Licznik: ${state.counter} ${state.checked}")
					.baseListeners(BaseListeners())
			button(key = 1, text = "Hello world", onClick = { increment() })
			checkBox(key = 2, text = "Checkbox", selected = state.checked, onSelect = { setChecked(it) })
			radioButton(key = 3, text = "Radio", selected = state.checked, onSelect = { setChecked(it) })
		}
	}

	private fun increment() = setState(state.copy(counter = state.counter + 1))

	private fun setChecked(checked: Boolean) = setState(state.copy(checked = checked))
}

fun SwingRenderBuilder.counter(key: Any) = component(::CounterComponent, UProps(key))
