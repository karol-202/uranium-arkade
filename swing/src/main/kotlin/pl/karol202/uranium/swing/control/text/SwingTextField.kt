package pl.karol202.uranium.swing.control.text

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.core.util.RenderBuilder
import pl.karol202.uranium.swing.SwingAbstractComponent
import pl.karol202.uranium.swing.SwingNative
import pl.karol202.uranium.swing.SwingNativeComponent
import pl.karol202.uranium.swing.SwingRenderBuilder
import javax.swing.JTextField

class SwingTextField(props: Props) : SwingAbstractComponent<SwingTextField.Props>(props)
{
	data class Props(override val abstractTextProps: SwingAbstractTextComponent.Props) : UProps by abstractTextProps,
	                                                                                     SwingNativeComponent.PropsProvider<Props>,
	                                                                                     SwingAbstractTextComponent.PropsProvider<Props>
	{
		override val swingProps = abstractTextProps.swingProps

		override fun withSwingProps(builder: SwingNativeComponent.Props.() -> SwingNativeComponent.Props) =
				copy(abstractTextProps = abstractTextProps.withSwingProps(builder))

		override fun withAbstractTextProps(builder: SwingAbstractTextComponent.Props.() -> SwingAbstractTextComponent.Props) =
				copy(abstractTextProps = abstractTextProps.builder())
	}

	private val native = JTextField()

	override fun RenderBuilder<SwingNative>.render()
	{
		+ abstractTextComponent(native = native, props = props.abstractTextProps)
	}
}

fun SwingRenderBuilder.textField(key: Any = AutoKey) =
		component(::SwingTextField, SwingTextField.Props(SwingAbstractTextComponent.props(key)))
