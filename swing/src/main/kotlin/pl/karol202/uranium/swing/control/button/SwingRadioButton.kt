package pl.karol202.uranium.swing.control.button

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.util.SwingAbstractComponent
import pl.karol202.uranium.swing.util.SwingRenderBuilder
import pl.karol202.uranium.swing.util.SwingRenderScope
import javax.swing.JRadioButton

class SwingRadioButton(private val native: JRadioButton,
                       initialProps: SwingToggleButton.Props) : SwingAbstractComponent<SwingToggleButton.Props>(initialProps)
{
	override fun SwingRenderBuilder.render()
	{
		+ toggleButton(native = { native }, props = props)
	}
}

fun SwingRenderScope.radioButton(native: () -> JRadioButton = ::JRadioButton,
                                 key: Any = AutoKey,
                                 props: SwingToggleButton.Props = SwingToggleButton.Props(key)) =
		component({ SwingRadioButton(native(), it) }, props)
