package pl.karol202.uranium.swing.control.button

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.util.SwingAbstractComponent
import pl.karol202.uranium.swing.util.SwingRenderBuilder
import javax.swing.JButton

class SwingButton(private val native: JButton,
                  props: SwingAbstractButton.Props) : SwingAbstractComponent<SwingAbstractButton.Props>(props)
{
	override fun SwingRenderBuilder.render()
	{
		+ abstractButton(native = { native }, props = props)
	}
}

fun SwingRenderBuilder.button(native: () -> JButton = ::JButton,
                              key: Any = AutoKey,
                              props: SwingAbstractButton.Props = SwingAbstractButton.Props(key)) =
		component({ SwingButton(native(), it) }, props)
