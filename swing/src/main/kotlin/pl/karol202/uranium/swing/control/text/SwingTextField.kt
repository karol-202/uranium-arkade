package pl.karol202.uranium.swing.control.text

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.core.util.Builder
import pl.karol202.uranium.swing.SwingAbstractComponent
import pl.karol202.uranium.swing.SwingNativeComponent
import pl.karol202.uranium.swing.SwingRenderBuilder
import javax.swing.JTextField

class SwingTextField(private val native: JTextField,
                     props: Props) : SwingAbstractComponent<SwingTextField.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 override val abstractTextProps: SwingAbstractTextComponent.Props = SwingAbstractTextComponent.Props()) :
			UProps,
			SwingNativeComponent.PropsProvider<Props>,
			SwingAbstractTextComponent.PropsProvider<Props>,
			PropsProvider<Props>
	{
		override val swingProps = abstractTextProps.swingProps
		override val textFieldProps = this

		override fun withSwingProps(builder: Builder<SwingNativeComponent.Props>) =
				copy(abstractTextProps = abstractTextProps.withSwingProps(builder))

		override fun withAbstractTextProps(builder: Builder<SwingAbstractTextComponent.Props>) =
				copy(abstractTextProps = abstractTextProps.builder())

		override fun withTextFieldProps(builder: Builder<Props>) = builder()
	}

	interface PropsProvider<S : PropsProvider<S>> : UProps
	{
		val textFieldProps: Props

		fun withTextFieldProps(builder: Builder<Props>): S
	}

	override fun SwingRenderBuilder.render()
	{
		+ abstractTextComponent(native = { native }, props = props.abstractTextProps)
	}
}

fun SwingRenderBuilder.textField(native: () -> JTextField = ::JTextField,
                                 key: Any = AutoKey,
                                 props: SwingTextField.Props = SwingTextField.Props(key)) =
		component({ SwingTextField(native(), it) }, props)
