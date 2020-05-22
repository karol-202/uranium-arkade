package pl.karol202.uranium.swing.component

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.native.UNative
import pl.karol202.uranium.swing.*
import pl.karol202.uranium.swing.renderScope

abstract class SwingAbstractNativeComponent<P : UProps>(props: P) : SwingAbstractComponent<P>(props)
{
	abstract override val native: UNative<Swing>

	final override fun render() = renderScope().render()

	protected abstract fun SwingRenderScope.render(): List<SwingElement<*>>
}
