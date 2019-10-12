package pl.karol202.uranium.swing.control.text

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.core.context.InvalidateableContext
import pl.karol202.uranium.swing.SwingNativeComponent
import pl.karol202.uranium.swing.SwingNativeWrapper
import pl.karol202.uranium.swing.util.*
import java.awt.event.ActionListener
import javax.swing.JTextField

class SwingTextField(private val native: JTextField,
                     initialProps: Props) : SwingAbstractComponent<SwingTextField.Props>(initialProps)
{
	data class Props(override val key: Any = AutoKey,
	                 override val abstractTextProps: SwingAbstractTextComponent.Props = SwingAbstractTextComponent.Props(),
	                 val columns: Prop<Int> = Prop.NoValue,
	                 val horizontalAlign: Prop<HorizontalAlign> = Prop.NoValue,
	                 val scrollOffset: Prop<Int> = Prop.NoValue,
	                 val onApply: Prop<(String) -> Unit> = Prop.NoValue) : UProps,
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

	// For security reasons (JPasswordField), native.text mustn't be called unless onApply is specified
	private val actionListener = ActionListener { props.onApply.value?.invoke(native.text) }

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
		+ abstractTextComponent(native = { native }, props = props.abstractTextProps)
	}

	override fun onUpdate(previousProps: Props) = native.apply {
		props.columns.ifPresent { columns = it }
		props.horizontalAlign.ifPresent { horizontalAlignment = it.code }
		props.scrollOffset.ifPresent { scrollOffset = it }
	}.unit
}

fun SwingRenderScope.textField(native: () -> JTextField = ::JTextField,
                               key: Any = AutoKey,
                               props: SwingTextField.Props = SwingTextField.Props(key)) =
		component({ SwingTextField(native(), it) }, props)

private typealias STFProvider<P> = SwingTextField.PropsProvider<P>
fun <P : STFProvider<P>> SwingElement<P>.withTextFieldProps(builder: Builder<SwingTextField.Props>) = withProps { withTextFieldProps(builder) }
fun <P : STFProvider<P>> SwingElement<P>.columns(columns: Int) = withTextFieldProps { copy(columns = columns.prop()) }
fun <P : STFProvider<P>> SwingElement<P>.horizontalAlign(align: HorizontalAlign) = withTextFieldProps { copy(horizontalAlign = align.prop()) }
fun <P : STFProvider<P>> SwingElement<P>.scrollOffset(offset: Int) = withTextFieldProps { copy(scrollOffset = offset.prop()) }
fun <P : STFProvider<P>> SwingElement<P>.onApply(onApply: (String) -> Unit) = withTextFieldProps { copy(onApply = onApply.prop()) }
