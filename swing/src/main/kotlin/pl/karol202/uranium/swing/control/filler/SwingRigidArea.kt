package pl.karol202.uranium.swing.control.filler

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.component
import pl.karol202.uranium.swing.SwingNativeComponent
import pl.karol202.uranium.swing.nativeComponent
import pl.karol202.uranium.swing.util.*
import java.awt.Dimension
import javax.swing.Box

class SwingRigidArea(initialProps: Props) : SwingAbstractComponent<SwingRigidArea.Props>(initialProps)
{
	data class Props(override val key: Any = AutoKey,
	                 override val swingProps: SwingNativeComponent.Props = SwingNativeComponent.Props(),
	                 val size: Dimension) : UProps,
	                                        SwingNativeComponent.PropsProvider<Props>
	{
		override fun withSwingProps(builder: Builder<SwingNativeComponent.Props>) = copy(swingProps = swingProps.builder())
	}

	override fun SwingRenderBuilder.render()
	{
		+ nativeComponent(native = { createNative() }, props = props.swingProps)
	}

	private fun createNative() = Box.createRigidArea(props.size) as SwingNative
}

fun SwingRenderScope.rigidArea(key: Any = AutoKey,
                               size: Dimension,
                               props: SwingRigidArea.Props = SwingRigidArea.Props(key = key, size = size)) =
		component(::SwingRigidArea, props)
