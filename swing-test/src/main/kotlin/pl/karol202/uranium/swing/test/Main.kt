package pl.karol202.uranium.swing.test

import pl.karol202.uranium.core.common.BasicProps
import pl.karol202.uranium.core.common.UState
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.control.button.*
import pl.karol202.uranium.swing.control.combobox.*
import pl.karol202.uranium.swing.control.label.label
import pl.karol202.uranium.swing.control.label.text
import pl.karol202.uranium.swing.control.list.*
import pl.karol202.uranium.swing.control.progress.maximum
import pl.karol202.uranium.swing.control.progress.minimum
import pl.karol202.uranium.swing.control.progress.progressBar
import pl.karol202.uranium.swing.control.progress.value
import pl.karol202.uranium.swing.control.slider.*
import pl.karol202.uranium.swing.control.text.columns
import pl.karol202.uranium.swing.control.text.onTextChange
import pl.karol202.uranium.swing.control.text.text
import pl.karol202.uranium.swing.control.text.textField
import pl.karol202.uranium.swing.frame.SwingFrame
import pl.karol202.uranium.swing.layout.gridbag.Fill
import pl.karol202.uranium.swing.layout.gridbag.Weights
import pl.karol202.uranium.swing.layout.gridbag.cell
import pl.karol202.uranium.swing.layout.gridbag.gridBagLayout
import pl.karol202.uranium.swing.util.Insets
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
	                 val checked: Boolean = false,
	                 val items: List<String> = listOf("Kot", "Pies", "Koń"),
	                 val selectedItem: String? = null,
	                 val sliderValue: Int = 0,
	                 val selectedListItems: List<String> = listOf("Koń")) : UState

	override fun SwingRenderBuilder.render()
	{
		+ gridBagLayout {
			+ cell(0, 0) {
				label(key = 0).text("Tekst: ${state.text} Stan: ${state.checked} Slider: ${state.sliderValue}")
			}
			+ cell(1, 0, weights = Weights(1.0, 0.0), fill = Fill.HORIZONTAL) {
				textField(key = 1).text(state.text).onTextChange { setText(it) }.columns(50)
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
				comboBox<String>(key = 5).items(state.items).renderer { props ->
					label().text(props.item?.let { "Zwierz: $it" } ?: "Brak")
				}.editable(true).editor { props ->
					editorComponent(initialValue = props.item, onApply = {
						addItem(it)
						props.onEdit(it)
						setSelectedItem(it)
					})
				}.selectedItem(state.selectedItem).onSelect { setSelectedItem(it) }
			}
			+ cell(0, 1, fill = Fill.HORIZONTAL) {
				slider(key = 7).minimum(50).maximum(100).value(state.sliderValue).onChange { setSliderValue(it) }
			}
			+ cell(2, 1, fill = Fill.HORIZONTAL, insets = Insets(8, 8, 8, 8)) {
				list<String>(key = 8).items(state.items).selectionMode(SelectionMode.MULTIPLE_INTERNAL_SELECTION)
						.selectedItems(state.selectedListItems).onSelect { setSelectedListItems(it) }
			}
			+ cell(3, 1, fill = Fill.HORIZONTAL) {
				progressBar(key = 6).minimum(0).maximum(200).value((state.sliderValue - 50) * 4)
			}
		}
	}

	private fun setText(text: String) = setState { copy(text = text) }

	private fun setChecked(checked: Boolean) = setState { copy(checked = checked) }

	private fun addItem(item: String) = setState { copy(items = items + item) }

	private fun setSelectedItem(item: String) = setState { copy(selectedItem = item) }

	private fun setSliderValue(value: Int) = setState { copy(sliderValue = value) }

	private fun setSelectedListItems(items: List<String>) = setState { copy(selectedListItems = items) }
}

fun SwingRenderScope.counter(key: Any) = component(::CounterComponent, BasicProps(key))
