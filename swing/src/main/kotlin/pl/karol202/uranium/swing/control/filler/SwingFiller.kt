package pl.karol202.uranium.swing.control.filler

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.SwingNativeComponent
import pl.karol202.uranium.swing.nativeComponent
import pl.karol202.uranium.swing.util.Builder
import pl.karol202.uranium.swing.util.SwingAbstractComponent
import pl.karol202.uranium.swing.util.SwingRenderBuilder
import pl.karol202.uranium.swing.util.SwingRenderScope
import java.awt.Dimension
import javax.swing.Box

class SwingFiller(initialProps: Props) : SwingAbstractComponent<SwingFiller.Props>(initialProps)
{
	data class Props(override val key: Any = AutoKey,
	                 override val swingProps: SwingNativeComponent.Props = SwingNativeComponent.Props(),
	                 val minimumSize: Dimension,
	                 val preferredSize: Dimension,
	                 val maximumSize: Dimension) : UProps,
	                                               SwingNativeComponent.PropsProvider<Props>
	{
		override fun withSwingProps(builder: Builder<SwingNativeComponent.Props>) = copy(swingProps = swingProps.builder())
	}

	override fun SwingRenderBuilder.render()
	{
		+ nativeComponent(native = { createNative() }, props = props.swingProps)
	}

	private fun createNative() = Box.Filler(props.minimumSize, props.preferredSize, props.maximumSize)
}

fun SwingRenderScope.filler(key: Any = AutoKey,
                            minimumSize: Dimension,
                            preferredSize: Dimension,
                            maximumSize: Dimension,
                            props: SwingFiller.Props = SwingFiller.Props(key = key,
                                                                         minimumSize = minimumSize,
                                                                         preferredSize = preferredSize,
                                                                         maximumSize = maximumSize)) =
		component(::SwingFiller, props)
