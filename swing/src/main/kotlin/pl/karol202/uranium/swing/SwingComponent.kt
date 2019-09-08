package pl.karol202.uranium.swing

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.AbstractComponent
import pl.karol202.uranium.swing.util.*

abstract class SwingComponent<P : SwingComponent.Props>(props: P) : AbstractComponent<SwingNative, P>(props)
{
	open class Props(key: Any,
	                 val baseListeners: BaseListeners?,
	                 val enabled: Boolean,
	                 val visible: Boolean) : UProps(key)

	protected abstract val native: SwingNative

	private val componentListener = ComponentListenerDelegate { props.baseListeners?.componentListener }
	private val focusListener = FocusListenerDelegate { props.baseListeners?.focusListener }
	private val hierarchyBoundsListener = HierarchyBoundsListenerDelegate { props.baseListeners?.hierarchyBoundsListener }
	private val hierarchyListener = HierarchyListenerDelegate { props.baseListeners?.hierarchyListener }
	private val inputMethodListener = InputMethodListenerDelegate { props.baseListeners?.inputMethodListener }
	private val keyListener = KeyListenerDelegate { props.baseListeners?.keyListener }
	private val mouseListener = MouseListenerDelegate { props.baseListeners?.mouseListener }
	private val mouseMotionListener = MouseMotionListenerDelegate { props.baseListeners?.mouseMotionListener }
	private val mouseWheelListener = MouseWheelListenerDelegate { props.baseListeners?.mouseWheelListener }
	private val ancestorListener = AncestorListenerDelegate { props.baseListeners?.ancestorListener }
	private val vetoableChangeListener = VetoableChangeListenerDelegate { props.baseListeners?.vetoableChangeListener }

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

	final override fun render() = renderChildren().also { onUpdate() }

	protected abstract fun renderChildren(): List<SwingElement<*>>

	protected open fun onUpdate()
	{
		native.isEnabled = props.enabled
		native.isVisible = props.visible
	}
}
