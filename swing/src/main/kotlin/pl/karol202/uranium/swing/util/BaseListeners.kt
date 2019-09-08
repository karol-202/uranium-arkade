package pl.karol202.uranium.swing.util

import java.awt.event.*
import java.beans.VetoableChangeListener
import javax.swing.event.AncestorListener

data class BaseListeners(val componentListener: ComponentListener? = null,
                         val focusListener: FocusListener? = null,
                         val hierarchyBoundsListener: HierarchyBoundsListener? = null,
                         val hierarchyListener: HierarchyListener? = null,
                         val inputMethodListener: InputMethodListener? = null,
                         val keyListener: KeyListener? = null,
                         val mouseListener: MouseListener? = null,
                         val mouseMotionListener: MouseMotionListener? = null,
                         val mouseWheelListener: MouseWheelListener? = null,
                         val ancestorListener: AncestorListener? = null,
                         val vetoableChangeListener: VetoableChangeListener? = null)
