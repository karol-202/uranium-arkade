package pl.karol202.uranium.swing.control.button

import pl.karol202.uranium.core.util.RenderBuilder
import pl.karol202.uranium.swing.SwingAbstractComponent
import pl.karol202.uranium.swing.SwingNative
import javax.swing.JButton

class SwingButton(props: SwingAbstractButton.Props) : SwingAbstractComponent<SwingAbstractButton.Props>(props)
{
	private val native = JButton()

	override fun RenderBuilder<SwingNative>.render()
	{
		+ abstractButton(native, props)
	}
}
