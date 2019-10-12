package pl.karol202.uranium.swing.control.combobox

import pl.karol202.uranium.core.tree.renderToNode
import pl.karol202.uranium.swing.SwingContextImpl
import pl.karol202.uranium.swing.SwingSingleWrapper
import pl.karol202.uranium.swing.singleWrapper
import pl.karol202.uranium.swing.util.SwingElement
import pl.karol202.uranium.swing.util.SwingEmptyRenderScope
import pl.karol202.uranium.swing.util.SwingRenderScope
import pl.karol202.uranium.swing.util.SwingTreeNode
import java.awt.BorderLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.ComboBoxEditor
import javax.swing.JPanel

class CustomComboBoxEditor<E>(var renderFunction: SwingRenderScope.(Props<E>) -> SwingElement<*>) : ComboBoxEditor
{
	companion object
	{
		private val ACTION_EVENT = ActionEvent(this, ActionEvent.ACTION_PERFORMED, null)
	}

	data class Props<E>(val item: E?,
	                    val onEdit: (E) -> Unit)

	private val listeners = mutableListOf<ActionListener>()
	private val nativeContainer = JPanel(BorderLayout())
	private val context = SwingContextImpl(nativeContainer)
	private var rootNode: SwingTreeNode<SwingSingleWrapper.Props>? = null

	private var item: E? = null

	override fun getEditorComponent() = nativeContainer

	override fun selectAll() { }

	override fun getItem() = item

	@Suppress("UNCHECKED_CAST")
	override fun setItem(rawItem: Any?)
	{
		this.item = rawItem as? E
		val props = Props(item) { onEdit(it) }
		reuse(props) ?: render(props)
	}

	private fun reuse(props: Props<E>) = rootNode?.reuse(renderRootElement(props))

	private fun render(props: Props<E>) = renderRootElement(props).renderToNode(context).also { rootNode = it }

	private fun renderRootElement(props: Props<E>) = SwingEmptyRenderScope.singleWrapper { renderFunction(props) }

	private fun onEdit(newItem: E)
	{
		item = newItem
		fireEvent()
	}

	private fun fireEvent() = listeners.forEach { it.actionPerformed(ACTION_EVENT) }

	override fun addActionListener(listener: ActionListener)
	{
		listeners += listener
	}

	override fun removeActionListener(listener: ActionListener)
	{
		listeners -= listener
	}
}
