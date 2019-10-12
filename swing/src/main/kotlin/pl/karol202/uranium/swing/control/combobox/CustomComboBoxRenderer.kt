package pl.karol202.uranium.swing.control.combobox

import pl.karol202.uranium.core.tree.renderToNode
import pl.karol202.uranium.swing.SwingContextImpl
import pl.karol202.uranium.swing.SwingSingleWrapper
import pl.karol202.uranium.swing.singleWrapper
import pl.karol202.uranium.swing.util.SwingElement
import pl.karol202.uranium.swing.util.SwingEmptyRenderScope
import pl.karol202.uranium.swing.util.SwingRenderScope
import pl.karol202.uranium.swing.util.SwingTreeNode
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
	private val context = SwingContextImpl(nativeContainer)
	private var rootNode: SwingTreeNode<SwingSingleWrapper.Props>? = null

	override fun getListCellRendererComponent(list: JList<out E>?, value: E?, index: Int, selected: Boolean, focus: Boolean): JPanel
	{
		val props = Props(value, index, selected, focus)
		reuse(props) ?: render(props)
		return nativeContainer
	}

	private fun reuse(props: Props<E>) = rootNode?.reuse(renderRootElement(props))

	private fun render(props: Props<E>) = renderRootElement(props).renderToNode(context).also { rootNode = it }

	private fun renderRootElement(props: Props<E>) = SwingEmptyRenderScope.singleWrapper { renderFunction(props) }
}
