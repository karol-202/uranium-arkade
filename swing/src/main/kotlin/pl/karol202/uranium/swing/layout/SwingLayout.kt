package pl.karol202.uranium.swing.layout

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderScope
import pl.karol202.uranium.swing.*
import pl.karol202.uranium.swing.Builder
import pl.karol202.uranium.swing.component.SwingAbstractAppComponent
import pl.karol202.uranium.swing.component.SwingContainerComponent
import pl.karol202.uranium.swing.component.nativeComponent
import pl.karol202.uranium.swing.util.*
import java.awt.Container
import java.awt.LayoutManager
import javax.swing.JPanel

class SwingLayout(initialProps: Props) : SwingAbstractAppComponent<SwingLayout.Props>(initialProps)
{
	data class Props(override val key: Any,
	                 override val swingProps: SwingContainerComponent.Props,
	                 val content: List<SwingElement<*>>,
	                 val layoutUpdater: (Container, LayoutManager?) -> LayoutManager) : UProps,
	                                                                                    SwingContainerComponent.PropsProvider<Props>
	{
		override fun withSwingProps(builder: Builder<SwingContainerComponent.Props>) = copy(swingProps = swingProps.builder())
	}

	private val nativeComponent = JPanel()

	override fun URenderScope<Swing>.render() =
			nativeComponent(nativeComponent = { nativeComponent }, props = props.swingProps.copy(children = props.content))

	override fun onUpdate(previousProps: Props?) = nativeComponent.update {
		layout = props.layoutUpdater(nativeComponent, nativeComponent.layout)
	}
}

internal fun SwingRenderScope.layout(key: Any = AutoKey,
                                     swingProps: SwingContainerComponent.Props = SwingContainerComponent.Props(),
                                     content: List<SwingElement<*>>,
                                     layoutUpdater: (Container, LayoutManager?) -> LayoutManager) =
		component(::SwingLayout, SwingLayout.Props(key, swingProps, content, layoutUpdater))
