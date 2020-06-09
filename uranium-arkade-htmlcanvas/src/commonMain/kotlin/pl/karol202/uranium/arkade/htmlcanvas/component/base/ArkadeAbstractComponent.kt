package pl.karol202.uranium.arkade.htmlcanvas.component.base

import pl.karol202.uranium.arkade.htmlcanvas.Arkade
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderBuilder
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.component.UAbstractComponent
import pl.karol202.uranium.core.native.UNative
import pl.karol202.uranium.core.render.render

abstract class ArkadeAbstractComponent<P : UProps>(props: P) : UAbstractComponent<Arkade, P>(props)
{
	override val native: UNative<Arkade>? = null

	private val renderFunction: ArkadeRenderBuilder.() -> Unit = { render() }

	final override fun render() = renderFunction.render()

	protected abstract fun ArkadeRenderBuilder.render()
}
