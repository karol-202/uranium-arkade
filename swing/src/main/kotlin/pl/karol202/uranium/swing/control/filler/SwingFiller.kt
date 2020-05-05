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

class SwingFiller(initialProps: Props) : SwingAbstractAppComponent<SwingFiller.Props>(initialProps)
{
	data class Props(override val key: Any = AutoKey,
	                 override val swingProps: SwingContainerComponent.Props = SwingContainerComponent.Props(),
	                 val minimumSize: Dimension,
	                 val preferredSize: Dimension,
	                 val maximumSize: Dimension) : UProps,
	                                               SwingContainerComponent.PropsProvider<Props>
	{
		override fun withSwingProps(builder: Builder<SwingContainerComponent.Props>) = copy(swingProps = swingProps.builder())
	}

	override fun URenderScope<Swing>.render() =
			nativeComponent(nativeComponent = { createNativeComponent() }, props = props.swingProps)


	private fun createNativeComponent() = Box.Filler(props.minimumSize, props.preferredSize, props.maximumSize)
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
