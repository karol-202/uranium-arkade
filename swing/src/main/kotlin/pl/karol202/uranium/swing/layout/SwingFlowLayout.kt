package pl.karol202.uranium.swing.layout

import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.SwingRenderBuilder
import pl.karol202.uranium.swing.util.BaseListeners
import java.awt.FlowLayout

class SwingFlowLayout(props: Props) : SwingLayout<SwingFlowLayout.Props>(props)
{
	enum class Align(val code: Int)
	{
		LEFT(FlowLayout.LEFT),
		CENTER(FlowLayout.CENTER),
		RIGHT(FlowLayout.RIGHT),
		LEADING(FlowLayout.LEADING),
		TRAILING(FlowLayout.TRAILING)
	}

	class Props(key: Any,
	            baseListeners: BaseListeners,
	            enabled: Boolean,
	            visible: Boolean,
	            block: SwingRenderBuilder.() -> Unit,
	            val align: Align?,
	            val alignOnBaseline: Boolean?,
	            val horizontalGap: Int?,
	            val verticalGap: Int?) : SwingLayout.Props(key, baseListeners, enabled, visible, block)

	override fun createLayout() = FlowLayout().apply {
		props.align?.let { alignment = it.code }
		props.alignOnBaseline?.let { alignOnBaseline = it }
		props.horizontalGap?.let { hgap = it }
		props.verticalGap?.let { vgap = it }
	}
}

fun SwingRenderBuilder.flowLayout(key: Any,
                                  baseListeners: BaseListeners = BaseListeners(),
                                  enabled: Boolean = true,
                                  visible: Boolean = true,
                                  align: SwingFlowLayout.Align? = null,
                                  alignOnBaseline: Boolean? = null,
                                  horizontalGap: Int? = null,
                                  verticalGap: Int? = null,
                                  block: SwingRenderBuilder.() -> Unit) =
		component(::SwingFlowLayout, SwingFlowLayout.Props(key, baseListeners, enabled, visible, block,
		                                                   align, alignOnBaseline, horizontalGap, verticalGap))
