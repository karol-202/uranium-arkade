package pl.karol202.uranium.swing.control.button

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.core.util.Builder
import pl.karol202.uranium.swing.util.Prop
import pl.karol202.uranium.swing.util.prop
import pl.karol202.uranium.swing.SwingAbstractComponent
import pl.karol202.uranium.swing.SwingElement
import pl.karol202.uranium.swing.SwingNativeComponent
import pl.karol202.uranium.swing.SwingRenderBuilder
import javax.swing.JCheckBox

class SwingCheckBox(private val native: JCheckBox,
                    props: Props) : SwingAbstractComponent<SwingCheckBox.Props>(props)
{
	data class Props(override val key: Any,
	                 override val toggleButtonProps: SwingToggleButton.Props = SwingToggleButton.Props(),
	                 val borderPaintedFlat: Prop<Boolean> = Prop.NoValue) : UProps,
	                                                                        SwingNativeComponent.PropsProvider<Props>,
	                                                                        SwingAbstractButton.PropsProvider<Props>,
	                                                                        SwingToggleButton.PropsProvider<Props>,
	                                                                        PropsProvider<Props>
	{
		override val swingProps = toggleButtonProps.swingProps
		override val abstractButtonProps = toggleButtonProps.abstractButtonProps
		override val checkBoxProps = this

		override fun withSwingProps(builder: Builder<SwingNativeComponent.Props>) =
				copy(toggleButtonProps = toggleButtonProps.withSwingProps(builder))

		override fun withAbstractButtonProps(builder: Builder<SwingAbstractButton.Props>) =
				copy(toggleButtonProps = toggleButtonProps.withAbstractButtonProps(builder))

		override fun withToggleButtonProps(builder: Builder<SwingToggleButton.Props>) =
				copy(toggleButtonProps = toggleButtonProps.builder())

		override fun withCheckBoxProps(builder: Builder<Props>) = builder()
	}

	interface PropsProvider<S : PropsProvider<S>> : UProps
	{
		val checkBoxProps: Props

		fun withCheckBoxProps(builder: Builder<Props>): S
	}

	override fun SwingRenderBuilder.render()
	{
		+ toggleButton(native = { native }, props = props.toggleButtonProps)
	}
}

fun SwingRenderBuilder.checkBox(native: () -> JCheckBox = ::JCheckBox,
                                key: Any = AutoKey,
                                props: SwingCheckBox.Props = SwingCheckBox.Props(key)) =
		component({ SwingCheckBox(native(), it) }, props)

private typealias SCBProvider<P> = SwingCheckBox.PropsProvider<P>
fun <P : SCBProvider<P>> SwingElement<P>.withCheckBoxProps(builder: Builder<SwingCheckBox.Props>) = withProps { withCheckBoxProps(builder) }
fun <P : SCBProvider<P>> SwingElement<P>.borderPaintedFlat(flat: Boolean) = withCheckBoxProps { copy(borderPaintedFlat = flat.prop()) }
