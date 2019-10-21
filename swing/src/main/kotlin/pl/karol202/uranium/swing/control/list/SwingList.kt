package pl.karol202.uranium.swing.control.list

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.core.context.InvalidateableContext
import pl.karol202.uranium.swing.SwingNativeComponent
import pl.karol202.uranium.swing.SwingNativeWrapper
import pl.karol202.uranium.swing.nativeComponent
import pl.karol202.uranium.swing.util.*
import java.awt.Color
import javax.swing.DropMode
import javax.swing.JList
import javax.swing.ListModel
import javax.swing.event.ListSelectionListener

class SwingList<E>(private val native: JList<E>,
                   initialProps: Props<E>) : SwingAbstractComponent<SwingList.Props<E>>(initialProps)
{
	data class Props<E>(override val key: Any = AutoKey,
	                    override val swingProps: SwingNativeComponent.Props = SwingNativeComponent.Props(),
	                    val items: Prop<List<E>> = Prop.NoValue,
	                    val selectedItems: Prop<List<E>> = Prop.NoValue,
	                    val dragEnabled: Prop<Boolean> = Prop.NoValue,
	                    val fixedCellWidth: Prop<Int> = Prop.NoValue,
	                    val fixedCellHeight: Prop<Int> = Prop.NoValue,
	                    val orientation: Prop<Orientation> = Prop.NoValue,
	                    val dropMode: Prop<DropMode> = Prop.NoValue,
	                    val prototypeCellValue: Prop<E> = Prop.NoValue,
	                    val selectionMode: Prop<SelectionMode> = Prop.NoValue,
	                    val selectionBackground: Prop<Color?> = Prop.NoValue,
	                    val selectionForeground: Prop<Color?> = Prop.NoValue,
	                    val onSelect: Prop<(List<E>) -> Unit> = Prop.NoValue) : UProps,
	                                                                          SwingNativeComponent.PropsProvider<Props<E>>,
	                                                                          PropsProvider<Props<E>, E>
	{
		override val listProps = this

		override fun withSwingProps(builder: Builder<SwingNativeComponent.Props>) =
				copy(swingProps = swingProps.builder())

		override fun withListProps(builder: Builder<Props<E>>) = builder()
	}

	interface PropsProvider<S : PropsProvider<S, E>, E> : UProps
	{
		val listProps: Props<E>

		fun withListProps(builder: Builder<Props<E>>): S
	}

	private val listSelectionListener = ListSelectionListener { onSelect() }

	private val mutableModel = MutableListModel(props.items.value ?: emptyList())

	override fun onAttach(parentContext: InvalidateableContext<SwingNativeWrapper>)
	{
		native.addListSelectionListener(listSelectionListener)
		native.model = mutableModel
	}

	override fun onDetach(parentContext: InvalidateableContext<SwingNativeWrapper>)
	{
		native.removeListSelectionListener(listSelectionListener)
	}

	override fun SwingRenderBuilder.render()
	{
		+ nativeComponent(native = { native }, props = props.swingProps)
	}

	override fun onUpdate(previousProps: Props<E>?) = native.apply {
		props.items.ifPresent { mutableModel.items = it }
		props.selectedItems.ifPresent { setSelectedItems(it) }
		props.dragEnabled.ifPresent { dragEnabled = it }
		props.fixedCellWidth.ifPresent { fixedCellWidth = it }
		props.fixedCellHeight.ifPresent { fixedCellHeight = it }
		props.orientation.ifPresent { layoutOrientation = it.code }
		props.dropMode.ifPresent { dropMode = it }
		props.prototypeCellValue.ifPresent { prototypeCellValue = it }
		props.selectionMode.ifPresent { selectionMode = it.code }
		props.selectionBackground.ifPresent { selectionBackground = it }
		props.selectionForeground.ifPresent { selectionForeground = it }
	}.unit

	private fun JList<E>.setSelectedItems(selectedItems: List<E>)
	{
		selectedIndices = selectedItems.mapNotNull { model.indexOf(it) }.toIntArray()
	}

	private fun ListModel<E>.indexOf(item: E) = (0 until size).firstOrNull { getElementAt(it) == item }

	private fun onSelect()
	{
		props.onSelect.value?.invoke(native.selectedValuesList)
		invalidate()
	}
}

fun <E> SwingRenderScope.list(native: () -> JList<E> = ::JList,
                              key: Any = AutoKey,
                              props: SwingList.Props<E> = SwingList.Props(key)) =
		component({ SwingList(native(), it) }, props)

private typealias SCBProvider<P, E> = SwingList.PropsProvider<P, E>
fun <P : SCBProvider<P, E>, E> SwingElement<P>.withListProps(builder: Builder<SwingList.Props<E>>) =
		withProps { withListProps(builder) }
fun <P : SCBProvider<P, E>, E> SwingElement<P>.items(items: List<E>) =
		withListProps { copy(items = items.prop()) }
fun <P : SCBProvider<P, E>, E> SwingElement<P>.selectedItems(items: List<E>) =
		withListProps { copy(selectedItems = items.prop()) }
fun <P : SCBProvider<P, E>, E> SwingElement<P>.dragEnabled(enabled: Boolean) =
		withListProps { copy(dragEnabled = enabled.prop()) }
fun <P : SCBProvider<P, E>, E> SwingElement<P>.fixedCellWidth(width: Int) =
		withListProps { copy(fixedCellWidth = width.prop()) }
fun <P : SCBProvider<P, E>, E> SwingElement<P>.fixedCellHeight(height: Int) =
		withListProps { copy(fixedCellHeight = height.prop()) }
fun <P : SCBProvider<P, E>, E> SwingElement<P>.orientation(orientation: Orientation) =
		withListProps { copy(orientation = orientation.prop()) }
fun <P : SCBProvider<P, E>, E> SwingElement<P>.dropMode(dropMode: DropMode) =
		withListProps { copy(dropMode = dropMode.prop()) }
fun <P : SCBProvider<P, E>, E> SwingElement<P>.prototypeCellValue(value: E) =
		withListProps { copy(prototypeCellValue = value.prop()) }
fun <P : SCBProvider<P, E>, E> SwingElement<P>.selectionMode(mode: SelectionMode) =
		withListProps { copy(selectionMode = mode.prop()) }
fun <P : SCBProvider<P, E>, E> SwingElement<P>.selectionBackground(background: Color?) =
		withListProps { copy(selectionBackground = background.prop()) }
fun <P : SCBProvider<P, E>, E> SwingElement<P>.selectionForeground(foreground: Color?) =
		withListProps { copy(selectionForeground = foreground.prop()) }
fun <P : SCBProvider<P, E>, E> SwingElement<P>.onSelect(onSelect: (List<E>) -> Unit) =
		withListProps { copy(onSelect = onSelect.prop()) }
