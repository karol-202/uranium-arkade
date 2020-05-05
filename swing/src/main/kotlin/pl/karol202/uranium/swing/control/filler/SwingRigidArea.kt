package pl.karol202.uranium.swing.control.filler

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderScope
import pl.karol202.uranium.swing.component.SwingContainerComponent
import pl.karol202.uranium.swing.component.nativeComponent
import pl.karol202.uranium.swing.Builder
import pl.karol202.uranium.swing.Swing
import pl.karol202.uranium.swing.SwingRenderScope
import pl.karol202.uranium.swing.component.SwingAbstractAppComponent
import java.awt.Dimension
import javax.swing.Box
import javax.swing.JComponent

class SwingRigidArea(initialProps: Props) : SwingAbstractAppComponent<SwingRigidArea.Props>(initialProps)
{
	data class Props(override val key: Any = AutoKey,
	                 override val swingProps: SwingContainerComponent.Props = SwingContainerComponent.Props(),
	                 val size: Dimension) : UProps,
	                                        SwingContainerComponent.PropsProvider<Props>
	{
		override fun withSwingProps(builder: Builder<SwingContainerComponent.Props>) = copy(swingProps = swingProps.builder())
	}

	override fun URenderScope<Swing>.render() =
			nativeComponent(nativeComponent = { createNativeComponent() }, props = props.swingProps)

	private fun createNativeComponent() = Box.createRigidArea(props.size) as JComponent
}

fun SwingRenderScope.rigidArea(key: Any = AutoKey,
                               size: Dimension,
                               props: SwingRigidArea.Props = SwingRigidArea.Props(key = key, size = size)) =
		component(::SwingRigidArea, props)
