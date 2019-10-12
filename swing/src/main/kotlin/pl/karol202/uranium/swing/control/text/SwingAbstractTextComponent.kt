package pl.karol202.uranium.swing.control.text

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.SwingNativeComponent
import pl.karol202.uranium.swing.nativeComponent
import pl.karol202.uranium.swing.util.*
import java.awt.Color
import java.awt.Insets
import javax.swing.DropMode
import javax.swing.text.*

class SwingAbstractTextComponent(private val native: JTextComponent,
                                 initialProps: Props) : SwingAbstractComponent<SwingAbstractTextComponent.Props>(initialProps)
{
    data class Props(override val key: Any = AutoKey,
                     override val swingProps: SwingNativeComponent.Props = SwingNativeComponent.Props(),
                     val text: Prop<String?> = Prop.NoValue,
                     val caret: Prop<Caret> = Prop.NoValue,
                     val highlighter: Prop<Highlighter> = Prop.NoValue,
                     val keymap: Prop<Keymap> = Prop.NoValue,
                     val navigationFilter: Prop<NavigationFilter> = Prop.NoValue,
                     val selectionColor: Prop<Color> = Prop.NoValue,
                     val selectedTextColor: Prop<Color> = Prop.NoValue,
                     val disabledTextColor: Prop<Color> = Prop.NoValue,
                     val caretColor: Prop<Color> = Prop.NoValue,
                     val editable: Prop<Boolean> = Prop.NoValue,
                     val dragEnabled: Prop<Boolean> = Prop.NoValue,
                     val dropMode: Prop<DropMode> = Prop.NoValue,
                     val margin: Prop<Insets> = Prop.NoValue,
                     val focusAccelerator: Prop<Char> = Prop.NoValue,
                     val caretPosition: Prop<Int> = Prop.NoValue,
                     val selectionStart: Prop<Int> = Prop.NoValue,
                     val selectionEnd: Prop<Int> = Prop.NoValue,
                     val onCaretMove: Prop<(dot: Int, mark: Int) -> Unit> = Prop.NoValue,
                     val onTextChange: Prop<(String) -> Unit> = Prop.NoValue) : UProps,
                                                                                SwingNativeComponent.PropsProvider<Props>,
                                                                                PropsProvider<Props>
    {
        override val abstractTextProps = this

        override fun withSwingProps(builder: Builder<SwingNativeComponent.Props>) = copy(swingProps = swingProps.builder())

        override fun withAbstractTextProps(builder: Builder<Props>) = builder()
    }

    interface PropsProvider<S : PropsProvider<S>> : UProps
    {
        val abstractTextProps: Props

        fun withAbstractTextProps(builder: Builder<Props>): S
    }

    private val caretListener = CaretListenerDelegate { props.onCaretMove.value }
    private val documentListener = TextChangeListenerDelegate { props.onTextChange.value?.let { onTextChange ->
        { if(it != props.text.value) onTextChange(it) } }
    }

    override fun onAttach(parentContext: SwingInvalidateableContext)
    {
        super.onAttach(parentContext)
        native.addCaretListener(caretListener)
        native.document.addDocumentListener(documentListener)
    }

    override fun onDetach(parentContext: SwingInvalidateableContext)
    {
        super.onDetach(parentContext)
        native.removeCaretListener(caretListener)
        native.document.removeDocumentListener(documentListener)
    }

    override fun SwingRenderBuilder.render()
    {
        + nativeComponent(native = { native }, props = props.swingProps)
    }

	override fun onUpdate(previousProps: Props) = native.apply {
        props.text.ifPresent { if(it != text) text = it }
        props.caret.ifPresent { caret = it }
        props.highlighter.ifPresent { highlighter = it }
        props.keymap.ifPresent { keymap = it }
        props.navigationFilter.ifPresent { navigationFilter = it }
        props.selectionColor.ifPresent { selectionColor = it }
        props.selectedTextColor.ifPresent { selectedTextColor = it }
        props.disabledTextColor.ifPresent { disabledTextColor = it }
        props.caretColor.ifPresent { caretColor = it }
        props.editable.ifPresent { isEditable = it }
        props.dragEnabled.ifPresent { dragEnabled = it }
        props.dropMode.ifPresent { dropMode = it }
    }.unit
}

fun SwingRenderScope.abstractTextComponent(native: () -> JTextComponent,
                                           key: Any = AutoKey,
                                           props: SwingAbstractTextComponent.Props = SwingAbstractTextComponent.Props(key)) =
        component({ SwingAbstractTextComponent(native(), it) }, props)

private typealias SATCProvider<P> = SwingAbstractTextComponent.PropsProvider<P>
fun <P : SATCProvider<P>> SwingElement<P>.withAbstractTextProps(builder: Builder<SwingAbstractTextComponent.Props>) =
        withProps { withAbstractTextProps(builder) }
fun <P : SATCProvider<P>> SwingElement<P>.text(text: String?) = withAbstractTextProps { copy(text = text.prop()) }
fun <P : SATCProvider<P>> SwingElement<P>.caret(caret: Caret) = withAbstractTextProps { copy(caret = caret.prop()) }
fun <P : SATCProvider<P>> SwingElement<P>.highlighter(highlighter: Highlighter) = withAbstractTextProps { copy(highlighter = highlighter.prop()) }
fun <P : SATCProvider<P>> SwingElement<P>.keymap(keymap: Keymap) = withAbstractTextProps { copy(keymap = keymap.prop()) }
fun <P : SATCProvider<P>> SwingElement<P>.navigationFilter(filter: NavigationFilter) = withAbstractTextProps { copy(navigationFilter = filter.prop()) }
fun <P : SATCProvider<P>> SwingElement<P>.selectionColor(color: Color) = withAbstractTextProps { copy(selectionColor = color.prop()) }
fun <P : SATCProvider<P>> SwingElement<P>.selectedTextColor(color: Color) = withAbstractTextProps { copy(selectedTextColor = color.prop()) }
fun <P : SATCProvider<P>> SwingElement<P>.disabledTextColor(color: Color) = withAbstractTextProps { copy(disabledTextColor = color.prop()) }
fun <P : SATCProvider<P>> SwingElement<P>.caretColor(color: Color) = withAbstractTextProps { copy(caretColor = color.prop()) }
fun <P : SATCProvider<P>> SwingElement<P>.editable(editable: Boolean) = withAbstractTextProps { copy(editable = editable.prop()) }
fun <P : SATCProvider<P>> SwingElement<P>.dragEnabled(enabled: Boolean) = withAbstractTextProps { copy(dragEnabled = enabled.prop()) }
fun <P : SATCProvider<P>> SwingElement<P>.dropMode(mode: DropMode) = withAbstractTextProps { copy(dropMode = mode.prop()) }
fun <P : SATCProvider<P>> SwingElement<P>.margin(margin: Insets) = withAbstractTextProps { copy(margin = margin.prop()) }
fun <P : SATCProvider<P>> SwingElement<P>.focusAccelerator(char: Char) = withAbstractTextProps { copy(focusAccelerator = char.prop()) }
fun <P : SATCProvider<P>> SwingElement<P>.caretPosition(position: Int) = withAbstractTextProps { copy(caretPosition = position.prop()) }
fun <P : SATCProvider<P>> SwingElement<P>.selectionStart(start: Int) = withAbstractTextProps { copy(selectionStart = start.prop()) }
fun <P : SATCProvider<P>> SwingElement<P>.selectionEnd(end: Int) = withAbstractTextProps { copy(selectionEnd = end.prop()) }
fun <P : SATCProvider<P>> SwingElement<P>.onCaretMove(onMove: (dot: Int, mark: Int) -> Unit) = withAbstractTextProps { copy(onCaretMove = onMove.prop()) }
fun <P : SATCProvider<P>> SwingElement<P>.onTextChange(onChange: (String) -> Unit) = withAbstractTextProps { copy(onTextChange = onChange.prop()) }
