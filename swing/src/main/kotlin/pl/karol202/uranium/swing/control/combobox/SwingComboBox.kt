package pl.karol202.uranium.swing.control.combobox

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.core.context.InvalidateableContext
import pl.karol202.uranium.swing.SwingNativeComponent
import pl.karol202.uranium.swing.SwingNativeWrapper
import pl.karol202.uranium.swing.nativeComponent
import pl.karol202.uranium.swing.util.*
import javax.swing.JComboBox

class SwingComboBox<E>(private val native: JComboBox<E>,
                       initialProps: Props<E>) : SwingAbstractComponent<SwingComboBox.Props<E>>(initialProps)
{
	data class Props<E>(override val key: Any = AutoKey,
	                    override val swingProps: SwingNativeComponent.Props = SwingNativeComponent.Props(),
	                    val items: Prop<List<E>> = Prop.NoValue,
	                    val renderer: Prop<SwingRenderScope.(CustomComboBoxRenderer.Props<E>) -> SwingElement<*>> = Prop.NoValue,
	                    val editor: Prop<SwingRenderScope.(CustomComboBoxEditor.Props<E>) -> SwingElement<*>> = Prop.NoValue,
	                    val editable: Prop<Boolean> = Prop.NoValue,
	                    val popupVisible: Prop<Boolean> = Prop.NoValue,
	                    val lightweightPopup: Prop<Boolean> = Prop.NoValue,
	                    val maximumRowCount: Prop<Int> = Prop.NoValue,
	                    val prototypeDisplayValue: Prop<E?> = Prop.NoValue,
	                    val onSelect: Prop<(E) -> Unit> = Prop.NoValue,
	                    val onPopupShow: Prop<() -> Unit> = Prop.NoValue,
	                    val onPopupHide: Prop<() -> Unit> = Prop.NoValue,
	                    val onPopupCancel: Prop<() -> Unit> = Prop.NoValue) : UProps,
	                                                                          SwingNativeComponent.PropsProvider<Props<E>>,
	                                                                          PropsProvider<Props<E>, E>
	{
		override val comboBoxProps = this

		override fun withSwingProps(builder: Builder<SwingNativeComponent.Props>) =
				copy(swingProps = swingProps.builder())

		override fun withComboBoxProps(builder: Builder<Props<E>>) = builder()
	}

	interface PropsProvider<S : PropsProvider<S, E>, E> : UProps
	{
		val comboBoxProps: Props<E>

		fun withComboBoxProps(builder: Builder<Props<E>>): S
	}

	private val itemListener = ItemSelectListener { props.onSelect.value?.invoke(it as E) }
	private val popupListener = PopupListenerDelegate({ props.onPopupShow.value },
	                                                  { props.onPopupHide.value },
	                                                  { props.onPopupCancel.value })

	private val model = ListComboBoxModel(props.items.value ?: emptyList())

	private var renderer: CustomComboBoxRenderer<E>? = null
	private var editor: CustomComboBoxEditor<E>? = null

	override fun onAttach(parentContext: InvalidateableContext<SwingNativeWrapper>)
	{
		native.addItemListener(itemListener)
		native.addPopupMenuListener(popupListener)
		native.model = model
	}

	override fun onDetach(parentContext: InvalidateableContext<SwingNativeWrapper>)
	{
		native.removeItemListener(itemListener)
		native.removePopupMenuListener(popupListener)
	}

	override fun SwingRenderBuilder.render()
	{
		+ nativeComponent(native = { native }, props = props.swingProps)
	}

	override fun onUpdate(previousProps: Props<E>)
	{
		props.items.ifPresent { model.items = it }
		props.renderer.ifPresent { native.renderer = getRenderer(it) }
		props.editor.ifPresent { native.editor = getEditor(it) }
		props.editable.ifPresent { native.isEditable = it }
		props.popupVisible.ifPresent { native.isPopupVisible = it }
		props.lightweightPopup.ifPresent { native.isLightWeightPopupEnabled = it }
		props.maximumRowCount.ifPresent { native.maximumRowCount = it }
		props.prototypeDisplayValue.ifPresent { native.prototypeDisplayValue = it }
	}

	private fun getRenderer(renderFunction: SwingRenderScope.(CustomComboBoxRenderer.Props<E>) -> SwingElement<*>) =
			renderer?.also { it.renderFunction = renderFunction } ?: CustomComboBoxRenderer(renderFunction).also { renderer = it }

	private fun getEditor(renderFunction: SwingRenderScope.(CustomComboBoxEditor.Props<E>) -> SwingElement<*>) =
			editor?.also { it.renderFunction = renderFunction } ?: CustomComboBoxEditor(renderFunction).also { editor = it }
}

fun <E> SwingRenderScope.comboBox(native: () -> JComboBox<E> = ::JComboBox,
                                  key: Any = AutoKey,
                                  props: SwingComboBox.Props<E> = SwingComboBox.Props(key)) =
		component({ SwingComboBox(native(), it) }, props)

private typealias SCBProvider<P, E> = SwingComboBox.PropsProvider<P, E>
fun <P : SCBProvider<P, E>, E> SwingElement<P>.withComboBoxProps(builder: Builder<SwingComboBox.Props<E>>) =
		withProps { withComboBoxProps(builder) }
fun <P : SCBProvider<P, E>, E> SwingElement<P>.items(items: List<E>) =
		withComboBoxProps { copy(items = items.prop()) }
fun <P : SCBProvider<P, E>, E> SwingElement<P>.renderer(renderer: SwingRenderScope.(CustomComboBoxRenderer.Props<E>) -> SwingElement<*>) =
		withComboBoxProps { copy(renderer = renderer.prop()) }
fun <P : SCBProvider<P, E>, E> SwingElement<P>.editor(editor: SwingRenderScope.(CustomComboBoxEditor.Props<E>) -> SwingElement<*>) =
		withComboBoxProps { copy(editor = editor.prop()) }
fun <P : SCBProvider<P, E>, E> SwingElement<P>.editable(editable: Boolean) =
		withComboBoxProps { copy(editable = editable.prop()) }
fun <P : SCBProvider<P, E>, E> SwingElement<P>.popupVisible(visible: Boolean) =
		withComboBoxProps { copy(popupVisible = visible.prop()) }
fun <P : SCBProvider<P, E>, E> SwingElement<P>.lightweightPopup(popup: Boolean) =
		withComboBoxProps { copy(lightweightPopup = popup.prop()) }
fun <P : SCBProvider<P, E>, E> SwingElement<P>.maximumRowCount(count: Int) =
		withComboBoxProps { copy(maximumRowCount = count.prop()) }
fun <P : SCBProvider<P, E>, E> SwingElement<P>.prototypeDisplayValue(value: E?) =
		withComboBoxProps { copy(prototypeDisplayValue = value.prop()) }
fun <P : SCBProvider<P, E>, E> SwingElement<P>.onSelect(onSelect: (E) -> Unit) =
		withComboBoxProps { copy(onSelect = onSelect.prop()) }
fun <P : SCBProvider<P, E>, E> SwingElement<P>.onPopupShow(onPopupShow: () -> Unit) =
		withComboBoxProps { copy(onPopupShow = onPopupShow.prop()) }
fun <P : SCBProvider<P, E>, E> SwingElement<P>.onPopupHide(onPopupHide: () -> Unit) =
		withComboBoxProps { copy(onPopupHide = onPopupHide.prop()) }
fun <P : SCBProvider<P, E>, E> SwingElement<P>.onPopupCancel(onPopupCancel: () -> Unit) =
		withComboBoxProps { copy(onPopupCancel = onPopupCancel.prop()) }
