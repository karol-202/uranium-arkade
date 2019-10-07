package pl.karol202.uranium.swing.test

import pl.karol202.uranium.core.common.BasicProps
import pl.karol202.uranium.core.common.UState
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.control.button.*
import pl.karol202.uranium.swing.control.label.label
import pl.karol202.uranium.swing.control.label.text
import pl.karol202.uranium.swing.control.text.*
import pl.karol202.uranium.swing.frame.SwingFrame
import pl.karol202.uranium.swing.layout.grid.contentColumns
import pl.karol202.uranium.swing.layout.grid.gridLayout
import pl.karol202.uranium.swing.util.HorizontalAlign
import pl.karol202.uranium.swing.util.SwingRenderBuilder
import pl.karol202.uranium.swing.util.SwingRenderScope
import pl.karol202.uranium.swing.util.SwingStatefulComponent
import javax.swing.UIManager

fun main()
{
	UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")
	SwingFrame.withRoot { counter(0) }.withTitle("Uranium test").withSize(640, 480).show()
}

class CounterComponent(props: BasicProps) : SwingStatefulComponent<BasicProps, CounterComponent.State>(props, State())
{
	data class State(val text: String = "start",
	                 val checked: Boolean = false) : UState

	override fun SwingRenderBuilder.render()
	{
		+ gridLayout().contentColumns {
			column {
				+ label(key = 0).text("Tekst: ${state.text} Stan: ${state.checked}")
				+ textField(key = 1).text(state.text).onTextChange { setText(it) }.horizontalAlign(HorizontalAlign.CENTER)
						.columns(50)
			}
			column {
				+ checkBox(key = 2).text("Checkbox").selected(state.checked).onSelect { setChecked(it) }
				+ radioButton(key = 3).text("Radio").selected(state.checked).onSelect { setChecked(it) }
			}
			column {
				+ toggleButton(key = 4).text(state.text).selected(state.checked).onClick { setChecked(false) }
				+ label(key = 5).text("Placeholder")
			}
		}
	}

	private fun setText(text: String) = setState(state.copy(text = text))

	private fun setChecked(checked: Boolean) = setState(state.copy(checked = checked))
}

fun SwingRenderScope.counter(key: Any) = component(::CounterComponent, BasicProps(key))
