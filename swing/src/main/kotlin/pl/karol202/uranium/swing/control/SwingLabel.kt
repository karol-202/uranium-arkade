package pl.karol202.uranium.swing.control

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.BaseProps
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.util.Prop
import pl.karol202.uranium.core.util.RenderBuilder
import pl.karol202.uranium.core.util.buildComponent
import pl.karol202.uranium.core.util.prop
import pl.karol202.uranium.swing.*
import pl.karol202.uranium.swing.util.HorizontalAlign
import pl.karol202.uranium.swing.util.VerticalAlign
import javax.swing.Icon
import javax.swing.JLabel

class SwingLabel(props: Props) : SwingAbstractComponent<SwingLabel.Props>(props)
{
	data class Props(override val swingProps: SwingNativeComponent.Props,
	                 val text: Prop<String?> = Prop.NoValue,
	                 val icon: Prop<Icon?> = Prop.NoValue,
	                 val disabledIcon: Prop<Icon?> = Prop.NoValue,
	                 val iconTextGap: Prop<Int> = Prop.NoValue,
	                 val horizontalAlign: Prop<HorizontalAlign> = Prop.NoValue,
	                 val verticalAlign: Prop<VerticalAlign> = Prop.NoValue,
	                 val horizontalTextPosition: Prop<HorizontalAlign> = Prop.NoValue,
	                 val verticalTextPosition: Prop<VerticalAlign> = Prop.NoValue) : UProps by swingProps,
	                                                                                 SwingNativeComponent.Props.Provider<Props>
	{
		override fun withSwingProps(builder: SwingNativeComponent.Props.() -> SwingNativeComponent.Props) =
				copy(swingProps = swingProps.builder())
	}

	private val native = JLabel()

	override fun RenderBuilder<SwingNative>.render()
	{
		+ nativeComponent(native = native, props = props.swingProps)
		onUpdate()
	}

	private fun onUpdate() = native.apply {
		props.text.ifPresent { text = it }
		props.icon.ifPresent { icon = it }
		props.disabledIcon.ifPresent { disabledIcon = it }
		props.iconTextGap.ifPresent { iconTextGap = it }
		props.horizontalAlign.ifPresent { horizontalAlignment = it.code }
		props.verticalAlign.ifPresent { verticalAlignment = it.code }
		props.horizontalTextPosition.ifPresent { horizontalTextPosition = it.code }
		props.verticalTextPosition.ifPresent { verticalTextPosition = it.code }
	}
}

private typealias LabelBuilder = SwingComponentBuilder<SwingLabel.Props>

fun SwingRenderBuilder.label(key: Any = AutoKey) =
		buildComponent(::SwingLabel, SwingLabel.Props(SwingNativeComponent.Props(BaseProps(key))))
fun LabelBuilder.text(text: String) = withProps { copy(text = text.prop()) }
fun LabelBuilder.icon(icon: Icon) = withProps { copy(icon = icon.prop()) }
fun LabelBuilder.disabledIcon(icon: Icon) = withProps { copy(disabledIcon = icon.prop()) }
fun LabelBuilder.iconTextGap(gap: Int) = withProps { copy(iconTextGap = gap.prop()) }
fun LabelBuilder.horizontalAlign(align: HorizontalAlign) = withProps { copy(horizontalAlign = align.prop()) }
fun LabelBuilder.verticalAlign(align: VerticalAlign) = withProps { copy(verticalAlign = align.prop()) }
fun LabelBuilder.horizontalTextPosition(position: HorizontalAlign) = withProps { copy(horizontalTextPosition = position.prop()) }
fun LabelBuilder.verticalTextPosition(position: VerticalAlign) = withProps { copy(verticalTextPosition = position.prop()) }
