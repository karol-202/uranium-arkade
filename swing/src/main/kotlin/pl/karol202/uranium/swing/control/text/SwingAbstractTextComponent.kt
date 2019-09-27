package pl.karol202.uranium.swing.control.text

import pl.karol202.uranium.swing.SwingNativeComponent
import pl.karol202.uranium.swing.control.SwingControl
import pl.karol202.uranium.swing.util.BaseListeners
import java.awt.Color
import java.awt.Insets
import javax.swing.event.CaretEvent
import javax.swing.event.CaretListener
import javax.swing.text.Highlighter
import javax.swing.text.JTextComponent
import javax.swing.text.Keymap
import javax.swing.text.NavigationFilter

abstract class SwingAbstractTextComponent<P : SwingAbstractTextComponent.Props>(props: P) : SwingControl<P>(props)
{
    open class Props(key: Any,
                     baseListeners: BaseListeners?,
                     enabled: Boolean,
                     visible: Boolean,
                     val text: String?,
                     val caretPosition: Int,
                     val selectionStart: Int,
                     val selectionEnd: Int,
                     val highlighter: Highlighter,
                     val keymap: Keymap,
                     val navigationFilter: NavigationFilter,
                     val selectionColor: Color,
                     val selectedTextColor: Color,
                     val disabledTextColor: Color,
                     val caretColor: Color,
                     val editable: Boolean,
                     val margin: Insets,
                     val onCaretMove: ((CaretEvent) -> Unit)?) : SwingNativeComponent.Props(key, baseListeners, enabled, visible)

    abstract override val native: JTextComponent

    private val caretListeners = CaretListener { props.onCaretMove?.invoke(it) }

    override fun onUpdate()
    {
        super.onUpdate()
        native.text = props.text
        native.caretPosition = props.caretPosition
        native.selectionStart = props.selectionStart
        native.selectionEnd = props.selectionEnd
        native.highlighter = props.highlighter
        native.keymap = props.keymap
        native.navigationFilter = props.navigationFilter
        native.selectionColor = props.selectionColor
        native.selectedTextColor = props.selectedTextColor
        native.disabledTextColor = props.disabledTextColor
        native.caretColor = props.caretColor
        native.isEditable = props.editable
        native.margin = props.margin
    }
}
