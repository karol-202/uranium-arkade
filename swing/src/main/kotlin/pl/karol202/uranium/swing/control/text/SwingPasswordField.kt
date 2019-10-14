package pl.karol202.uranium.swing.control.text

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.core.context.InvalidateableContext
import pl.karol202.uranium.swing.SwingNativeComponent
import pl.karol202.uranium.swing.SwingNativeWrapper
import pl.karol202.uranium.swing.util.*
import java.awt.event.ActionListener
import javax.swing.JPasswordField

class SwingPasswordField(private val native: JPasswordField,
                         initialProps: Props) : SwingAbstractComponent<SwingPasswordField.Props>(initialProps)
{
	data class Props(override val key: Any = AutoKey,
	                 override val textFieldProps: SwingTextField.Props = SwingTextField.Props(),
	                 val echoChar: Prop<Char?> = Prop.NoValue,
	                 val onPasswordApply: Prop<(CharArray) -> Unit> = Prop.NoValue) : UProps,
	                                                                                  SwingNativeComponent.PropsProvider<Props>,
	                                                                                  SwingAbstractTextComponent.PropsProvider<Props>,
	                                                                                  SwingTextField.PropsProvider<Props>,
	                                                                                  PropsProvider<Props>
	{
		override val swingProps = textFieldProps.swingProps
		override val abstractTextProps = textFieldProps.abstractTextProps
		override val passwordFieldProps = this

		override fun withSwingProps(builder: Builder<SwingNativeComponent.Props>) =
				copy(textFieldProps = textFieldProps.withSwingProps(builder))

		override fun withAbstractTextProps(builder: Builder<SwingAbstractTextComponent.Props>) =
				copy(textFieldProps = textFieldProps.withAbstractTextProps(builder))

		override fun withTextFieldProps(builder: Builder<SwingTextField.Props>) =
				copy(textFieldProps = textFieldProps.builder())

		override fun withPasswordFieldProps(builder: Builder<Props>) = builder()
	}

	interface PropsProvider<S : PropsProvider<S>> : UProps
	{
		val passwordFieldProps: Props

		fun withPasswordFieldProps(builder: Builder<Props>): S
	}

	companion object
	{
		const val PASSWORD_VISIBLE_CHAR = 0
	}

	private val actionListener = ActionListener { props.onPasswordApply.value?.invoke(native.password) }

	override fun onAttach(parentContext: InvalidateableContext<SwingNativeWrapper>)
	{
		native.addActionListener(actionListener)
	}

	override fun onDetach(parentContext: InvalidateableContext<SwingNativeWrapper>)
	{
		native.removeActionListener(actionListener)
	}

	override fun SwingRenderBuilder.render()
	{
		+ textField(native = { native }, props = props.textFieldProps)
	}

	override fun onUpdate(previousProps: Props?) = native.apply {
		props.echoChar.ifPresent { echoChar = it ?: PASSWORD_VISIBLE_CHAR.toChar() }
	}.unit
}

fun SwingRenderScope.passwordField(native: () -> JPasswordField = ::JPasswordField,
                                   key: Any = AutoKey,
                                   props: SwingPasswordField.Props = SwingPasswordField.Props(key)) =
		component({ SwingPasswordField(native(), it) }, props)

private typealias SPFProvider<P> = SwingPasswordField.PropsProvider<P>
fun <P : SPFProvider<P>> SwingElement<P>.withPasswordFieldProps(builder: Builder<SwingPasswordField.Props>) =
		withProps { withPasswordFieldProps(builder) }
fun <P : SPFProvider<P>> SwingElement<P>.echoChar(char: Char?) =
		withPasswordFieldProps { copy(echoChar = char.prop()) }
fun <P : SPFProvider<P>> SwingElement<P>.onPasswordApply(listener: (CharArray) -> Unit) =
		withPasswordFieldProps { copy(onPasswordApply = listener.prop()) }
