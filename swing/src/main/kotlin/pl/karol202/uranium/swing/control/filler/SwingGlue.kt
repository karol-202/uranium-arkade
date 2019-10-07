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

class SwingGlue(initialProps: Props) : SwingAbstractComponent<SwingGlue.Props>(initialProps)
{
	data class Props(override val key: Any = AutoKey,
	                 override val swingProps: SwingNativeComponent.Props = SwingNativeComponent.Props(),
	                 val axis: GlueAxis) : UProps,
	                                       SwingNativeComponent.PropsProvider<Props>
	{
		override fun withSwingProps(builder: Builder<SwingNativeComponent.Props>) = copy(swingProps = swingProps.builder())
	}

	override fun SwingRenderBuilder.render()
	{
		+ nativeComponent(native = { createNative() }, props = props.swingProps)
	}

	private fun createNative() = props.axis.createGlue()
}

fun SwingRenderScope.glue(key: Any = AutoKey,
                          axis: GlueAxis,
                          props: SwingGlue.Props = SwingGlue.Props(key = key, axis = axis)) = component(::SwingGlue, props)
