package pl.karol202.uranium.swing.component

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.swing.SwingAbstractComponent
import pl.karol202.uranium.swing.SwingElement
import pl.karol202.uranium.swing.SwingRenderScope
import pl.karol202.uranium.swing.renderScope

abstract class SwingAbstractAppComponent<P : UProps>(props: P) : SwingAbstractComponent<P>(props)
{
	final override fun render() = listOf(renderScope().render())

	protected abstract fun SwingRenderScope.render(): SwingElement<*>
}
