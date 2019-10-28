package pl.karol202.uranium.swing.control.list

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import pl.karol202.uranium.core.schedule.renderToNodeAndWait
import pl.karol202.uranium.swing.util.SwingSingleWrapper
import pl.karol202.uranium.swing.util.singleWrapper
import pl.karol202.uranium.swing.util.*
import javax.swing.JList
import javax.swing.JPanel
import javax.swing.ListCellRenderer

class CustomListCellRenderer<E>(var renderFunction: SwingRenderScope.(Props<E>) -> SwingElement<*>) : ListCellRenderer<E>
{
	data class Props<E>(val item: E?,
	                    val index: Int,
	                    val selected: Boolean,
	                    val hasFocus: Boolean)

	private val nativeContainer = JPanel()
	private var rootNode: SwingTreeNode<SwingSingleWrapper.Props>? = null
	private val coroutineScope = CoroutineScope(Dispatchers.Main)
	private val scheduler = SwingBlockingRenderScheduler(coroutineScope)

	override fun getListCellRendererComponent(list: JList<out E>?, value: E?, index: Int, selected: Boolean, focus: Boolean) =
			nativeContainer.also { reuseOrRender(Props(value, index, selected, focus)) }

	private fun reuseOrRender(props: Props<E>) = runBlocking { reuse(props) ?: render(props) }

	private suspend fun reuse(props: Props<E>) = rootNode?.scheduleReuseAndWait(renderRootElement(props))

	private suspend fun render(props: Props<E>)
	{
		rootNode = scheduler.renderToNodeAndWait(renderRootElement(props), createContext())
	}

	private fun renderRootElement(props: Props<E>) = SwingEmptyRenderScope.singleWrapper { renderFunction(props) }

	private fun createContext() = SwingContextImpl(nativeContainer)
}
