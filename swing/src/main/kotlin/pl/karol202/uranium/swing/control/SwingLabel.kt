package pl.karol202.uranium.swing.control

import pl.karol202.uranium.core.common.CompositeProps
import pl.karol202.uranium.core.util.Prop
import pl.karol202.uranium.core.util.buildComponent
import pl.karol202.uranium.core.util.prop
import pl.karol202.uranium.swing.*
import pl.karol202.uranium.swing.util.BaseListeners
import pl.karol202.uranium.swing.util.HorizontalAlign
import pl.karol202.uranium.swing.util.VerticalAlign
import javax.swing.Icon
import javax.swing.JLabel

class SwingLabel(props: Props) : SwingAbstractComponent<SwingLabel.Props>(props)
{
	data class Props(override val parentProps: SwingComponent.Props = SwingComponent.Props(),
	                 val text: Prop<String?> = Prop.NoValue,
	                 val icon: Prop<Icon?> = Prop.NoValue,
	                 val disabledIcon: Prop<Icon?> = Prop.NoValue,
	                 val iconTextGap: Prop<Int> = Prop.NoValue,
	                 val horizontalAlign: Prop<HorizontalAlign> = Prop.NoValue,
	                 val verticalAlign: Prop<VerticalAlign> = Prop.NoValue,
	                 val horizontalTextPosition: Prop<HorizontalAlign> = Prop.NoValue,
	                 val verticalTextPosition: Prop<VerticalAlign> = Prop.NoValue) :
			CompositeProps<Props, SwingComponent.Props>(parentProps)
	{
		override fun withParentProps(parentProps: SwingComponent.Props) = copy(parentProps = parentProps)
	}

	override val native = JLabel()

	override fun onUpdate()
	{
		super.onUpdate()
		props.text.ifPresent { native.text = it }
		props.icon.ifPresent { native.icon = it }
		props.disabledIcon.ifPresent { native.disabledIcon = it }
		props.iconTextGap.ifPresent { native.iconTextGap = it }
		props.horizontalAlign.ifPresent { native.horizontalAlignment = it.code }
		props.verticalAlign.ifPresent { native.verticalAlignment = it.code }
		props.horizontalTextPosition.ifPresent { native.horizontalTextPosition = it.code }
		props.verticalTextPosition.ifPresent { native.verticalTextPosition = it.code }
	}
}

private typealias LabelBuilder = SwingComponentBuilder<SwingLabel.Props>

fun SwingRenderBuilder.label(key: Any) = buildComponent(::SwingLabel, SwingLabel.Props(key = key))
fun LabelBuilder.enabled(enabled: Boolean) = withProps { copy(enabled = enabled.prop()) }
fun LabelBuilder.visible(visible: Boolean) = withProps { copy(visible = visible.prop()) }
fun LabelBuilder.text(text: String) = withProps { copy(text = text.prop()) }
fun LabelBuilder.icon(icon: Icon) = withProps { copy(icon = icon.prop()) }
fun LabelBuilder.disabledIcon(disabledIcon: Icon) = withProps { copy(disabledIcon = disabledIcon.prop()) }
fun LabelBuilder.iconTextGap(iconTextGap: Int) = withProps { copy(iconTextGap = iconTextGap.prop()) }
fun LabelBuilder.horizontalAlign(align: HorizontalAlign) = withProps { copy(horizontalAlign = align.prop()) }
fun LabelBuilder.verticalAlign(align: VerticalAlign) = withProps { copy(verticalAlign = align.prop()) }
fun LabelBuilder.horizontalTextPosition(position: HorizontalAlign) = withProps { copy(horizontalTextPosition = position.prop()) }
fun LabelBuilder.verticalTextPosition(position: VerticalAlign) = withProps { copy(verticalTextPosition = position.prop()) }
