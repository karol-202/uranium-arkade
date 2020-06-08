package pl.karol202.uranium.arkade.canvas.component.base

import pl.karol202.uranium.arkade.canvas.WC
import pl.karol202.uranium.arkade.canvas.WCRenderBuilder
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.UAbstractComponent
import pl.karol202.uranium.core.native.UNative
import pl.karol202.uranium.core.render.render

abstract class WCAbstractComponent<P : UProps>(props: P) : UAbstractComponent<WC, P>(props)
{
	override val native: UNative<WC>? = null

	private val renderFunction: WCRenderBuilder.() -> Unit = { render() }

	final override fun render() = renderFunction.render()

	protected abstract fun WCRenderBuilder.render()
}
