package pl.karol202.uranium.swing.control.combobox

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import pl.karol202.uranium.core.schedule.renderToNode
import pl.karol202.uranium.swing.SwingContextImpl
import pl.karol202.uranium.swing.SwingSingleWrapper
import pl.karol202.uranium.swing.singleWrapper
import pl.karol202.uranium.swing.util.*
import javax.swing.JList
import javax.swing.JPanel
import javax.swing.ListCellRenderer

class CustomComboBoxRenderer<E>(var renderFunction: SwingRenderScope.(Props<E>) -> SwingElement<*>) : ListCellRenderer<E>
{
	data class Props<E>(val item: E?,
	                    val index: Int,
	                    val selected: Boolean,
	                    val hasFocus: Boolean)

	private val nativeContainer = JPanel()
	private var rootNode: SwingTreeNode<SwingSingleWrapper.Props>? = null
	private val coroutineScope = CoroutineScope(Dispatchers.Main)

	override fun getListCellRendererComponent(list: JList<out E>?, value: E?, index: Int, selected: Boolean, focus: Boolean) =
			nativeContainer.also { reuseOrRender(Props(value, index, selected, focus)) }

	private fun reuseOrRender(props: Props<E>) = reuse(props) ?: render(props)

	private fun reuse(props: Props<E>) = rootNode?.reuse(renderRootElement(props))

	private fun render(props: Props<E>)
	{
		rootNode = SwingBlockingRenderScheduler(coroutineScope).renderToNode(renderRootElement(props), createContext())
	}

	private fun renderRootElement(props: Props<E>) = SwingEmptyRenderScope.singleWrapper { renderFunction(props) }

	private fun createContext() = SwingContextImpl(nativeContainer)
}
