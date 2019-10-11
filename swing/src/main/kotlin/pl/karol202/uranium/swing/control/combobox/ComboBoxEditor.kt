package pl.karol202.uranium.swing.control.combobox

import pl.karol202.uranium.swing.SwingContextImpl
import pl.karol202.uranium.swing.util.SwingElement
import pl.karol202.uranium.swing.util.SwingEmptyRenderScope
import pl.karol202.uranium.swing.util.SwingRenderScope
import pl.karol202.uranium.swing.util.SwingRenderer
import java.awt.event.ActionListener
import javax.swing.ComboBoxEditor
import javax.swing.JPanel

class ComboBoxEditor<E>(private val renderFunction: SwingRenderScope.(Props<E>) -> SwingElement<*>) : ComboBoxEditor
{
	data class Props<E>(val item: E,
	                    val onEdit: (E) -> Unit)

	private val nativeContainer = JPanel()
	private val renderer = SwingRenderer()
	private val context = SwingContextImpl(nativeContainer)

	private var item: E? = null

	override fun getEditorComponent() = nativeContainer

	override fun getItem() = item

	@Suppress("UNCHECKED_CAST")
	override fun setItem(rawItem: Any?)
	{
		val item = rawItem as? E ?: return
		this.item = item
		render(Props(item) { onEdit(it) })
	}

	private fun render(props: Props<E>) = renderer.renderRoot(renderElement(props), context)

	private fun renderElement(props: Props<E>) = SwingEmptyRenderScope.renderFunction(props)

	private fun onEdit(newItem: E)
	{
		item = newItem
	}

	override fun selectAll() { }

	override fun addActionListener(l: ActionListener?) = TODO("not implemented")

	override fun removeActionListener(l: ActionListener?) = TODO("not implemented")
}
