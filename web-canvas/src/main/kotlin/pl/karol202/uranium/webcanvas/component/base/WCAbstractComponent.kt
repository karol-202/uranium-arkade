package pl.karol202.uranium.webcanvas.component.base

import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.UAbstractComponent
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.webcanvas.*

abstract class WCAbstractComponent<P : UProps>(props: P) : UAbstractComponent<WC, P>(props)
{
	private val renderFunction: WCRenderBuilder.() -> Unit = { render() }

	final override fun render() = renderFunction.render()

	protected abstract fun WCRenderBuilder.render()
}
