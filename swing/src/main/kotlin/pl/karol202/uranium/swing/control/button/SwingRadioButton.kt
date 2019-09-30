package pl.karol202.uranium.swing.control.button

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.core.util.RenderBuilder
import pl.karol202.uranium.swing.SwingAbstractComponent
import pl.karol202.uranium.swing.SwingNative
import pl.karol202.uranium.swing.SwingRenderBuilder
import javax.swing.JRadioButton

class SwingRadioButton(private val native: JRadioButton,
                       props: SwingAbstractButton.Props) : SwingAbstractComponent<SwingAbstractButton.Props>(props)
{
	override fun RenderBuilder<SwingNative>.render()
	{
		+ abstractButton({ native }, props)
	}
}

fun SwingRenderBuilder.radioButton(native: () -> JRadioButton = ::JRadioButton,
                                   key: Any = AutoKey,
                                   props: SwingAbstractButton.Props = SwingAbstractButton.Props(key)) =
		component({ SwingRadioButton(native(), it) }, props)
