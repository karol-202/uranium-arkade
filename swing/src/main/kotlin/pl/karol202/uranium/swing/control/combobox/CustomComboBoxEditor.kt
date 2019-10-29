package pl.karol202.uranium.swing.control.combobox

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import pl.karol202.uranium.swing.util.*
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
	private var rootNode: SwingTreeNode<SwingSingleWrapper.Props>? = null
	private val coroutineScope = CoroutineScope(Dispatchers.Main)
	private val scheduler = SwingBlockingRenderScheduler(coroutineScope)

	private var item: E? = null

	override fun getEditorComponent() = nativeContainer

	override fun selectAll() { }

	override fun getItem() = item

	@Suppress("UNCHECKED_CAST")
	override fun setItem(rawItem: Any?)
	{
		this.item = rawItem as? E
		//reuseOrRenderBlocking(Props(item) { onEdit(it) })
		// TODO Make CustomComboBoxEditor work again
	}

	/*private fun reuseOrRenderBlocking(props: Props<E>) = runBlocking { reuse(props) ?: render(props) }

	private suspend fun reuse(props: Props<E>) = rootNode?.scheduleReuseAndWait(renderRootElement(props))

	private suspend fun render(props: Props<E>)
	{
		rootNode = scheduler.renderToNodeAndWait(renderRootElement(props), createContext())
	}*/

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
