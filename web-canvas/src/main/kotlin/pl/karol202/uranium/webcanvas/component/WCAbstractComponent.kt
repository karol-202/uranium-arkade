package pl.karol202.uranium.webcanvas.component

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.UAbstractComponent
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.WCElement
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.renderScope

abstract class WCAbstractComponent<P : UProps>(props: P) : UAbstractComponent<WC, P>(props)
{
	final override fun render() = renderScope().render()

	protected abstract fun WCRenderScope.render(): List<WCElement<*>>

	protected fun WCElement<*>.asList() = listOf(this)
}
