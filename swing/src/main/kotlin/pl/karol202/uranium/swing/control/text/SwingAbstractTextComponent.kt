package pl.karol202.uranium.swing.control.text

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.core.context.InvalidateableContext
import pl.karol202.uranium.core.util.Builder
import pl.karol202.uranium.core.util.Prop
import pl.karol202.uranium.core.util.RenderBuilder
import pl.karol202.uranium.core.util.prop
import pl.karol202.uranium.swing.*
import java.awt.Color
import java.awt.Insets
import javax.swing.event.CaretEvent
import javax.swing.event.CaretListener
import javax.swing.text.Highlighter
import javax.swing.text.JTextComponent
import javax.swing.text.Keymap
import javax.swing.text.NavigationFilter

class SwingAbstractTextComponent(private val native: JTextComponent,
                                 props: Props) : SwingAbstractComponent<SwingAbstractTextComponent.Props>(props)
{
    companion object
    {
        fun props(key: Any) = Props(SwingNativeComponent.props(key))
    }

    data class Props(override val swingProps: SwingNativeComponent.Props,
                     val text: Prop<String?> = Prop.NoValue,
                     val caretPosition: Prop<Int> = Prop.NoValue,
                     val selectionStart: Prop<Int> = Prop.NoValue,
                     val selectionEnd: Prop<Int> = Prop.NoValue,
                     val highlighter: Prop<Highlighter> = Prop.NoValue,
                     val keymap: Prop<Keymap> = Prop.NoValue,
                     val navigationFilter: Prop<NavigationFilter> = Prop.NoValue,
                     val selectionColor: Prop<Color> = Prop.NoValue,
                     val selectedTextColor: Prop<Color> = Prop.NoValue,
                     val disabledTextColor: Prop<Color> = Prop.NoValue,
                     val caretColor: Prop<Color> = Prop.NoValue,
                     val editable: Prop<Boolean> = Prop.NoValue,
                     val margin: Prop<Insets> = Prop.NoValue,
                     val onCaretMove: Prop<(CaretEvent) -> Unit> = Prop.NoValue) : UProps by swingProps,
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

    private val caretListener = CaretListener { props.onCaretMove.value?.invoke(it) }

    override fun onAttach(parentContext: InvalidateableContext<SwingNative>)
    {
        super.onAttach(parentContext)
        native.addCaretListener(caretListener)
    }

    override fun onDetach(parentContext: InvalidateableContext<SwingNative>)
    {
        super.onDetach(parentContext)
        native.removeCaretListener(caretListener)
    }

    override fun RenderBuilder<SwingNative>.render()
    {
        + nativeComponent(native = native, props = props.swingProps)
        onUpdate()
    }

    private fun onUpdate() = native.apply {
        props.text.ifPresent { text = it }
        props.caretPosition.ifPresent { caretPosition = it }
        props.selectionStart.ifPresent { selectionStart = it }
        props.selectionEnd.ifPresent { selectionEnd = it }
        props.highlighter.ifPresent { highlighter = it }
        props.keymap.ifPresent { keymap = it }
        props.navigationFilter.ifPresent { navigationFilter = it }
        props.selectionColor.ifPresent { selectionColor = it }
        props.selectedTextColor.ifPresent { selectedTextColor = it }
        props.disabledTextColor.ifPresent { disabledTextColor = it }
        props.caretColor.ifPresent { caretColor = it }
        props.editable.ifPresent { isEditable = it }
        props.margin.ifPresent { margin = it }
    }
}

fun SwingRenderBuilder.abstractTextComponent(native: JTextComponent, props: SwingAbstractTextComponent.Props) =
        component({ SwingAbstractTextComponent(native, it) }, props)
fun <P : SwingAbstractTextComponent.PropsProvider<P>> SwingElement<P>.text(text: String?) =
        withProps { withAbstractTextProps { copy(text = text.prop()) } }
fun <P : SwingAbstractTextComponent.PropsProvider<P>> SwingElement<P>.caretPosition(position: Int) =
        withProps { withAbstractTextProps { copy(caretPosition = position.prop()) } }
fun <P : SwingAbstractTextComponent.PropsProvider<P>> SwingElement<P>.selectionStart(position: Int) =
        withProps { withAbstractTextProps { copy(selectionStart = position.prop()) } }
fun <P : SwingAbstractTextComponent.PropsProvider<P>> SwingElement<P>.selectionEnd(position: Int) =
        withProps { withAbstractTextProps { copy(selectionEnd = position.prop()) } }
fun <P : SwingAbstractTextComponent.PropsProvider<P>> SwingElement<P>.highlighter(highlighter: Highlighter) =
        withProps { withAbstractTextProps { copy(highlighter = highlighter.prop()) } }
fun <P : SwingAbstractTextComponent.PropsProvider<P>> SwingElement<P>.keymap(keymap: Keymap) =
        withProps { withAbstractTextProps { copy(keymap = keymap.prop()) } }
fun <P : SwingAbstractTextComponent.PropsProvider<P>> SwingElement<P>.navigationFilter(filter: NavigationFilter) =
        withProps { withAbstractTextProps { copy(navigationFilter = filter.prop()) } }
fun <P : SwingAbstractTextComponent.PropsProvider<P>> SwingElement<P>.selectionColor(color: Color) =
        withProps { withAbstractTextProps { copy(selectionColor = color.prop()) } }
fun <P : SwingAbstractTextComponent.PropsProvider<P>> SwingElement<P>.selectedTextColor(color: Color) =
        withProps { withAbstractTextProps { copy(selectedTextColor = color.prop()) } }
fun <P : SwingAbstractTextComponent.PropsProvider<P>> SwingElement<P>.disabledTextColor(color: Color) =
        withProps { withAbstractTextProps { copy(disabledTextColor = color.prop()) } }
fun <P : SwingAbstractTextComponent.PropsProvider<P>> SwingElement<P>.caretColor(color: Color) =
        withProps { withAbstractTextProps { copy(caretColor = color.prop()) } }
fun <P : SwingAbstractTextComponent.PropsProvider<P>> SwingElement<P>.editable(editable: Boolean) =
        withProps { withAbstractTextProps { copy(editable = editable.prop()) } }
fun <P : SwingAbstractTextComponent.PropsProvider<P>> SwingElement<P>.margin(margin: Insets) =
        withProps { withAbstractTextProps { copy(margin = margin.prop()) } }
fun <P : SwingAbstractTextComponent.PropsProvider<P>> SwingElement<P>.onCaretMove(onCaretMove: (CaretEvent) -> Unit) =
        withProps { withAbstractTextProps { copy(onCaretMove = onCaretMove.prop()) } }
