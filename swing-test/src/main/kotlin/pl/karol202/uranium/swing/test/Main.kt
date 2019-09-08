package pl.karol202.uranium.swing.test

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.common.UState
import pl.karol202.uranium.core.component.UBuilder
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.SwingBuilder
import pl.karol202.uranium.swing.SwingNative
import pl.karol202.uranium.swing.SwingStatefulComponent
import pl.karol202.uranium.swing.control.button.button
import pl.karol202.uranium.swing.control.label
import pl.karol202.uranium.swing.frame.SwingFrame
import pl.karol202.uranium.swing.layout.flowLayout
import javax.swing.UIManager

fun main()
{
	UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")
	SwingFrame.withRoot { counter(0) }.withTitle("Uranium test").withSize(640, 480).show()
}

class CounterComponent(props: UProps) : SwingStatefulComponent<UProps, CounterComponent.State>(props, State())
{
	data class State(val counter: Int = 0) : UState

	override fun UBuilder<SwingNative>.render()
	{
		flowLayout(key = 0) {
			label(key = 0, text = "Licznik: ${state.counter}")
			button(key = 1, text = "Hello world", onAction = { increment() })
		}
	}

	private fun increment() = setState(state.copy(counter = state.counter + 1))
}

fun SwingBuilder.counter(key: Any) = component(::CounterComponent, UProps(key))
