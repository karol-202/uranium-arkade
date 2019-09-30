package pl.karol202.uranium.swing.control.button

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.core.util.RenderBuilder
import pl.karol202.uranium.swing.SwingAbstractComponent
import pl.karol202.uranium.swing.SwingNative
import pl.karol202.uranium.swing.SwingRenderBuilder
import javax.swing.JCheckBox

class SwingCheckBox(private val native: JCheckBox,
                    props: SwingAbstractButton.Props) : SwingAbstractComponent<SwingAbstractButton.Props>(props)
{
	override fun RenderBuilder<SwingNative>.render()
	{
		+ abstractButton({ native }, props)
	}
}

fun SwingRenderBuilder.checkBox(native: () -> JCheckBox = ::JCheckBox,
                                key: Any = AutoKey,
                                props: SwingAbstractButton.Props = SwingAbstractButton.Props(key)) =
		component({ SwingCheckBox(native(), it) }, props)
