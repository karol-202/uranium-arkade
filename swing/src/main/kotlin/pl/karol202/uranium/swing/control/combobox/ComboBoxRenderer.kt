package pl.karol202.uranium.swing.control.combobox

import pl.karol202.uranium.swing.SwingContextImpl
import pl.karol202.uranium.swing.util.SwingElement
import pl.karol202.uranium.swing.util.SwingEmptyRenderScope
import pl.karol202.uranium.swing.util.SwingRenderScope
import pl.karol202.uranium.swing.util.SwingRenderer
import javax.swing.JList
import javax.swing.JPanel
import javax.swing.ListCellRenderer

class ComboBoxRenderer<E>(private val renderFunction: SwingRenderScope.(Props<E>) -> SwingElement<*>) : ListCellRenderer<E>
{
	data class Props<E>(val item: E,
	                    val index: Int,
	                    val selected: Boolean,
	                    val hasFocus: Boolean)

	private val nativeContainer = JPanel()
	private val renderer = SwingRenderer()
	private val context = SwingContextImpl(nativeContainer)

	override fun getListCellRendererComponent(list: JList<out E>?, value: E, index: Int, selected: Boolean, focus: Boolean): JPanel
	{
		context.clear()
		render(Props(value, index, selected, focus))
		return nativeContainer
	}

	private fun render(props: Props<E>) = renderer.renderRoot(renderElement(props), context)

	private fun renderElement(props: Props<E>) = SwingEmptyRenderScope.renderFunction(props)
}
