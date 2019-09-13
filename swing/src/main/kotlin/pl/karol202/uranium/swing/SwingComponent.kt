package pl.karol202.uranium.swing

import pl.karol202.uranium.core.common.BaseProps
import pl.karol202.uranium.core.common.CompositeProps
import pl.karol202.uranium.core.util.Prop
import pl.karol202.uranium.core.util.prop
import pl.karol202.uranium.swing.util.*

class SwingComponent(props: Props) : SwingAbstractComponent<SwingComponent.Props>(props)
{
	data class Props(override val parentProps: BaseProps = BaseProps(),
	                 val context: SwingContext,
	                 val native: SwingNative,
	                 val children: List<SwingElement<*>>,
	                 val baseListeners: Prop<BaseListeners> = Prop.NoValue,
	                 val enabled: Prop<Boolean> = Prop.NoValue,
	                 val visible: Prop<Boolean> = Prop.NoValue) : CompositeProps<Props, BaseProps>(parentProps)
	{
		override fun withParentProps(parentProps: BaseProps) = copy(parentProps = parentProps)
	}

	override val context = props.context

	private val baseListeners = props.baseListeners.value
	private val componentListener = ComponentListenerDelegate { baseListeners?.componentListener }
	private val focusListener = FocusListenerDelegate { baseListeners?.focusListener }
	private val hierarchyBoundsListener = HierarchyBoundsListenerDelegate { baseListeners?.hierarchyBoundsListener }
	private val hierarchyListener = HierarchyListenerDelegate { baseListeners?.hierarchyListener }
	private val inputMethodListener = InputMethodListenerDelegate { baseListeners?.inputMethodListener }
	private val keyListener = KeyListenerDelegate { baseListeners?.keyListener }
	private val mouseListener = MouseListenerDelegate { baseListeners?.mouseListener }
	private val mouseMotionListener = MouseMotionListenerDelegate { baseListeners?.mouseMotionListener }
	private val mouseWheelListener = MouseWheelListenerDelegate { baseListeners?.mouseWheelListener }
	private val ancestorListener = AncestorListenerDelegate { baseListeners?.ancestorListener }
	private val vetoableChangeListener = VetoableChangeListenerDelegate { baseListeners?.vetoableChangeListener }

	override fun onAttach(parentContext: InvalidateableSwingContext)
	{
		props.native.addComponentListener(componentListener)
		props.native.addFocusListener(focusListener)
		props.native.addHierarchyBoundsListener(hierarchyBoundsListener)
		props.native.addHierarchyListener(hierarchyListener)
		props.native.addInputMethodListener(inputMethodListener)
		props.native.addKeyListener(keyListener)
		props.native.addMouseListener(mouseListener)
		props.native.addMouseMotionListener(mouseMotionListener)
		props.native.addMouseWheelListener(mouseWheelListener)
		props.native.addAncestorListener(ancestorListener)
		props.native.addVetoableChangeListener(vetoableChangeListener)

		parentContext.attachNative(props.native)
	}

	override fun onDetach(parentContext: InvalidateableSwingContext)
	{
		props.native.removeComponentListener(componentListener)
		props.native.removeFocusListener(focusListener)
		props.native.removeHierarchyBoundsListener(hierarchyBoundsListener)
		props.native.removeHierarchyListener(hierarchyListener)
		props.native.removeInputMethodListener(inputMethodListener)
		props.native.removeKeyListener(keyListener)
		props.native.removeMouseListener(mouseListener)
		props.native.removeMouseMotionListener(mouseMotionListener)
		props.native.removeMouseWheelListener(mouseWheelListener)
		props.native.removeAncestorListener(ancestorListener)
		props.native.removeVetoableChangeListener(vetoableChangeListener)

		parentContext.detachNative(props.native)
	}

	override fun render() = props.children.also { onUpdate() }

	private fun onUpdate()
	{
		props.enabled.ifPresent { props.native.isEnabled = it }
		props.visible.ifPresent { props.native.isVisible = it }
	}
}

fun <P : CompositeProps<P, P2>, P2 : CompositeProps<P2, BaseProps>> SwingComponentBuilder<P>.key(key: Any) =
		withProps { withParentProps(parentProps.withParentProps(parentProps.parentProps.copy(key = key))) }

fun <P : CompositeProps<P, SwingComponent.Props>> SwingComponentBuilder<P>.baseListeners(baseListeners: BaseListeners) =
		withProps { withParentProps(parentProps.copy(baseListeners = baseListeners.prop())) }
