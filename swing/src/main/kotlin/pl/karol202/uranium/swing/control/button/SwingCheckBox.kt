package pl.karol202.uranium.swing.control.button

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.util.RenderBuilder
import pl.karol202.uranium.core.util.buildComponent
import pl.karol202.uranium.swing.SwingAbstractComponent
import pl.karol202.uranium.swing.SwingNative
import pl.karol202.uranium.swing.SwingRenderBuilder
import javax.swing.JCheckBox

class SwingCheckBox(props: SwingAbstractButton.Props) : SwingAbstractComponent<SwingAbstractButton.Props>(props)
{
	private val native = JCheckBox()

	override fun RenderBuilder<SwingNative>.render()
	{
		+ abstractButton(native, props)
	}
}

fun SwingRenderBuilder.checkBox(key: Any = AutoKey) = buildComponent(::SwingCheckBox, SwingAbstractButton.Props.fromKey(key))
