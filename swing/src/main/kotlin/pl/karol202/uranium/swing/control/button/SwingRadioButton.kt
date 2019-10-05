package pl.karol202.uranium.swing.control.button

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.util.SwingAbstractComponent
import pl.karol202.uranium.swing.util.SwingRenderBuilder
import javax.swing.JRadioButton

class SwingRadioButton(private val native: JRadioButton,
                       props: SwingToggleButton.Props) : SwingAbstractComponent<SwingToggleButton.Props>(props)
{
	override fun SwingRenderBuilder.render()
	{
		+ toggleButton(native = { native }, props = props)
	}
}

fun SwingRenderBuilder.radioButton(native: () -> JRadioButton = ::JRadioButton,
                                   key: Any = AutoKey,
                                   props: SwingToggleButton.Props = SwingToggleButton.Props(key)) =
		component({ SwingRadioButton(native(), it) }, props)
