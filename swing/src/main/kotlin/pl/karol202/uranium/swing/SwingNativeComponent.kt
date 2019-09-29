package pl.karol202.uranium.swing

import pl.karol202.uranium.core.common.BaseProps
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.core.util.Prop
import pl.karol202.uranium.core.util.RenderBuilder
import pl.karol202.uranium.core.util.prop
import pl.karol202.uranium.swing.util.*
import java.awt.event.*
import java.beans.VetoableChangeListener
import javax.swing.event.AncestorListener

class SwingNativeComponent(private val native: SwingNative,
                           private val contextOverride: SwingContext?,
                           props: Props) : SwingAbstractComponent<SwingNativeComponent.Props>(props)
{
	companion object
	{
		fun props(key: Any) = Props(BaseProps(key))
	}

	data class Props(val baseProps: BaseProps,
	                 val children: List<SwingElement<*>> = emptyList(),
	                 val componentListener: Prop<ComponentListener> = Prop.NoValue,
	                 val focusListener: Prop<FocusListener> = Prop.NoValue,
	                 val hierarchyBoundsListener: Prop<HierarchyBoundsListener> = Prop.NoValue,
	                 val hierarchyListener: Prop<HierarchyListener> = Prop.NoValue,
	                 val inputMethodListener: Prop<InputMethodListener> = Prop.NoValue,
	                 val keyListener: Prop<KeyListener> = Prop.NoValue,
	                 val mouseListener: Prop<MouseListener> = Prop.NoValue,
	                 val mouseMotionListener: Prop<MouseMotionListener> = Prop.NoValue,
	                 val mouseWheelListener: Prop<MouseWheelListener> = Prop.NoValue,
	                 val ancestorListener: Prop<AncestorListener> = Prop.NoValue,
	                 val vetoableChangeListener: Prop<VetoableChangeListener> = Prop.NoValue,
	                 val enabled: Prop<Boolean> = Prop.NoValue,
	                 val visible: Prop<Boolean> = Prop.NoValue) : UProps by baseProps,
	                                                              PropsProvider<Props>
	{
		override val swingProps = this

		override fun withSwingProps(builder: Props.() -> Props) = builder()
	}

	interface PropsProvider<S : PropsProvider<S>> : UProps
	{
		val swingProps: Props

		fun withSwingProps(builder: Props.() -> Props): S
	}

	override val context get() = contextOverride ?: super.context

	private val componentListener = ComponentListenerDelegate { props.componentListener.value }
	private val focusListener = FocusListenerDelegate { props.focusListener.value }
	private val hierarchyBoundsListener = HierarchyBoundsListenerDelegate { props.hierarchyBoundsListener.value }
	private val hierarchyListener = HierarchyListenerDelegate { props.hierarchyListener.value }
	private val inputMethodListener = InputMethodListenerDelegate { props.inputMethodListener.value }
	private val keyListener = KeyListenerDelegate { props.keyListener.value }
	private val mouseListener = MouseListenerDelegate { props.mouseListener.value }
	private val mouseMotionListener = MouseMotionListenerDelegate { props.mouseMotionListener.value }
	private val mouseWheelListener = MouseWheelListenerDelegate { props.mouseWheelListener.value }
	private val ancestorListener = AncestorListenerDelegate { props.ancestorListener.value }
	private val vetoableChangeListener = VetoableChangeListenerDelegate { props.vetoableChangeListener.value }

	override fun onAttach(parentContext: InvalidateableSwingContext)
	{
		native.addComponentListener(componentListener)
		native.addFocusListener(focusListener)
		native.addHierarchyBoundsListener(hierarchyBoundsListener)
		native.addHierarchyListener(hierarchyListener)
		native.addInputMethodListener(inputMethodListener)
		native.addKeyListener(keyListener)
		native.addMouseListener(mouseListener)
		native.addMouseMotionListener(mouseMotionListener)
		native.addMouseWheelListener(mouseWheelListener)
		native.addAncestorListener(ancestorListener)
		native.addVetoableChangeListener(vetoableChangeListener)

		parentContext.attachNative(native)
	}

	override fun onDetach(parentContext: InvalidateableSwingContext)
	{
		native.removeComponentListener(componentListener)
		native.removeFocusListener(focusListener)
		native.removeHierarchyBoundsListener(hierarchyBoundsListener)
		native.removeHierarchyListener(hierarchyListener)
		native.removeInputMethodListener(inputMethodListener)
		native.removeKeyListener(keyListener)
		native.removeMouseListener(mouseListener)
		native.removeMouseMotionListener(mouseMotionListener)
		native.removeMouseWheelListener(mouseWheelListener)
		native.removeAncestorListener(ancestorListener)
		native.removeVetoableChangeListener(vetoableChangeListener)

		parentContext.detachNative(native)
	}

	override fun RenderBuilder<SwingNative>.render()
	{
		+ props.children
		onUpdate()
	}

	private fun onUpdate() = native.apply {
		props.enabled.ifPresent { isEnabled = it }
		props.visible.ifPresent { isVisible = it }
	}
}

fun SwingRenderBuilder.nativeComponent(native: SwingNative, contextOverride: SwingContext? = null, props: SwingNativeComponent.Props) =
		component({ SwingNativeComponent(native, contextOverride, it) }, props)
fun <P : SwingNativeComponent.PropsProvider<P>> SwingElement<P>.componentListener(listener: ComponentListener) =
		withProps { withSwingProps { copy(componentListener = listener.prop()) } }
fun <P : SwingNativeComponent.PropsProvider<P>> SwingElement<P>.focusListener(listener: FocusListener) =
		withProps { withSwingProps { copy(focusListener = listener.prop()) } }
fun <P : SwingNativeComponent.PropsProvider<P>> SwingElement<P>.hierarchyBoundsListener(listener: HierarchyBoundsListener) =
		withProps { withSwingProps { copy(hierarchyBoundsListener = listener.prop()) } }
fun <P : SwingNativeComponent.PropsProvider<P>> SwingElement<P>.hierarchyListener(listener: HierarchyListener) =
		withProps { withSwingProps { copy(hierarchyListener = listener.prop()) } }
fun <P : SwingNativeComponent.PropsProvider<P>> SwingElement<P>.inputMethodListener(listener: InputMethodListener) =
		withProps { withSwingProps { copy(inputMethodListener = listener.prop()) } }
fun <P : SwingNativeComponent.PropsProvider<P>> SwingElement<P>.keyListener(listener: KeyListener) =
		withProps { withSwingProps { copy(keyListener = listener.prop()) } }
fun <P : SwingNativeComponent.PropsProvider<P>> SwingElement<P>.mouseListener(listener: MouseListener) =
		withProps { withSwingProps { copy(mouseListener = listener.prop()) } }
fun <P : SwingNativeComponent.PropsProvider<P>> SwingElement<P>.mouseMotionListener(listener: MouseMotionListener) =
		withProps { withSwingProps { copy(mouseMotionListener = listener.prop()) } }
fun <P : SwingNativeComponent.PropsProvider<P>> SwingElement<P>.mouseWheelListener(listener: MouseWheelListener) =
		withProps { withSwingProps { copy(mouseWheelListener = listener.prop()) } }
fun <P : SwingNativeComponent.PropsProvider<P>> SwingElement<P>.ancestorListener(listener: AncestorListener) =
		withProps { withSwingProps { copy(ancestorListener = listener.prop()) } }
fun <P : SwingNativeComponent.PropsProvider<P>> SwingElement<P>.vetoableChangeListener(listener: VetoableChangeListener) =
		withProps { withSwingProps { copy(vetoableChangeListener = listener.prop()) } }
fun <P : SwingNativeComponent.PropsProvider<P>> SwingElement<P>.enabled(enabled: Boolean) =
		withProps { withSwingProps { copy(enabled = enabled.prop()) } }
fun <P : SwingNativeComponent.PropsProvider<P>> SwingElement<P>.visible(visible: Boolean) =
		withProps { withSwingProps { copy(visible = visible.prop()) } }
