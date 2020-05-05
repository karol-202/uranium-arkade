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

class SwingGlue(initialProps: Props) : SwingAbstractAppComponent<SwingGlue.Props>(initialProps)
{
	data class Props(override val key: Any = AutoKey,
	                 override val swingProps: SwingContainerComponent.Props = SwingContainerComponent.Props(),
	                 val axis: GlueAxis) : UProps,
	                                       SwingContainerComponent.PropsProvider<Props>
	{
		override fun withSwingProps(builder: Builder<SwingContainerComponent.Props>) = copy(swingProps = swingProps.builder())
	}

	override fun URenderScope<Swing>.render() =
			nativeComponent(nativeComponent = { createNativeComponent() }, props = props.swingProps)

	private fun createNativeComponent() = props.axis.createGlue()
}

fun SwingRenderScope.glue(key: Any = AutoKey,
                          axis: GlueAxis,
                          props: SwingGlue.Props = SwingGlue.Props(key = key, axis = axis)) = component(::SwingGlue, props)
