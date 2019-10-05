package pl.karol202.uranium.swing.control.button

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.SwingNativeComponent
import pl.karol202.uranium.swing.util.*
import java.awt.event.ItemEvent
import java.awt.event.ItemListener
import javax.swing.JToggleButton

class SwingToggleButton(private val native: JToggleButton,
                        props: Props) : SwingAbstractComponent<SwingToggleButton.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 override val abstractButtonProps: SwingAbstractButton.Props = SwingAbstractButton.Props(),
	                 val onSelect: Prop<(Boolean) -> Unit> = Prop.NoValue) : UProps,
	                                                                         SwingNativeComponent.PropsProvider<Props>,
	                                                                         SwingAbstractButton.PropsProvider<Props>,
	                                                                         PropsProvider<Props>
	{
		override val swingProps = abstractButtonProps.swingProps
		override val toggleButtonProps = this

		override fun withSwingProps(builder: Builder<SwingNativeComponent.Props>) =
				copy(abstractButtonProps = abstractButtonProps.withSwingProps(builder))

		override fun withAbstractButtonProps(builder: Builder<SwingAbstractButton.Props>) =
				copy(abstractButtonProps = abstractButtonProps.builder())

		override fun withToggleButtonProps(builder: Builder<Props>) = builder()
	}

	interface PropsProvider<S : PropsProvider<S>> : UProps
	{
		val toggleButtonProps: Props

		fun withToggleButtonProps(builder: Builder<Props>): S
	}

	private val itemListener = ItemListener { props.onSelect.value?.invoke(it.stateChange == ItemEvent.SELECTED) }

	override fun onAttach(parentContext: InvalidateableSwingContext)
	{
		super.onAttach(parentContext)
		native.addItemListener(itemListener)
	}

	override fun onDetach(parentContext: InvalidateableSwingContext)
	{
		super.onDetach(parentContext)
		native.removeItemListener(itemListener)
	}

	override fun SwingRenderBuilder.render()
	{
		+ abstractButton(native = { native }, props = props.abstractButtonProps)
	}
}

fun SwingRenderBuilder.toggleButton(native: () -> JToggleButton = ::JToggleButton,
                                    key: Any = AutoKey,
                                    props: SwingToggleButton.Props = SwingToggleButton.Props(key)) =
		component({ SwingToggleButton(native(), it) }, props)

private typealias STBProvider<P> = SwingToggleButton.PropsProvider<P>
fun <P : STBProvider<P>> SwingElement<P>.withToggleButtonProps(builder: Builder<SwingToggleButton.Props>) = withProps { withToggleButtonProps(builder) }
fun <P : STBProvider<P>> SwingElement<P>.onSelect(onSelect: (Boolean) -> Unit) = withToggleButtonProps { copy(onSelect = onSelect.prop()) }
