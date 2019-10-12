package pl.karol202.uranium.swing.test

import pl.karol202.uranium.core.common.BasicProps
import pl.karol202.uranium.core.common.UState
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.control.button.*
import pl.karol202.uranium.swing.control.combobox.comboBox
import pl.karol202.uranium.swing.control.combobox.items
import pl.karol202.uranium.swing.control.combobox.renderer
import pl.karol202.uranium.swing.control.label.label
import pl.karol202.uranium.swing.control.label.text
import pl.karol202.uranium.swing.control.text.*
import pl.karol202.uranium.swing.frame.SwingFrame
import pl.karol202.uranium.swing.layout.flow.flowLayout
import pl.karol202.uranium.swing.layout.gridbag.Fill
import pl.karol202.uranium.swing.layout.gridbag.Weights
import pl.karol202.uranium.swing.layout.gridbag.cell
import pl.karol202.uranium.swing.layout.gridbag.gridBagLayout
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
		+ gridBagLayout {
			+ cell(0, 0) {
				label(key = 0).text("Tekst: ${state.text} Stan: ${state.checked}")
			}
			+ cell(1, 0, weights = Weights(1.0, 0.0), fill = Fill.HORIZONTAL) {
				textField(key = 1).text(state.text).onTextChange { setText(it) }.horizontalAlign(HorizontalAlign.CENTER)
						.columns(50)
			}
			+ cell(2, 0) {
				checkBox(key = 2).text("Checkbox").selected(state.checked).onSelect { setChecked(it) }
			}
			+ cell(3, 0) {
				radioButton(key = 3).text("Radio").selected(state.checked).onSelect { setChecked(it) }
			}
			+ cell(4, 0) {
				toggleButton(key = 4).text(state.text).selected(state.checked).onClick { setChecked(false) }
			}
			+ cell(4, 1, weights = Weights(1.0, 0.0)) {
				comboBox<String>(key = 5).items(listOf("Kot", "Pies", "Koń")).renderer {
					flowLayout {
						+ label().text("Zwierz: ${it.item}")
					}
				}
			}
		}
	}

	private fun setText(text: String) = setState { copy(text = text) }

	private fun setChecked(checked: Boolean) = setState { copy(checked = checked) }
}

fun SwingRenderScope.counter(key: Any) = component(::CounterComponent, BasicProps(key))
