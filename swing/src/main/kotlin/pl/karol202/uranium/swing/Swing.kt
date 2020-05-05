package pl.karol202.uranium.swing

import pl.karol202.uranium.core.common.UStateful
import pl.karol202.uranium.core.component.UAbstractComponent
import pl.karol202.uranium.core.component.UNativeComponent
import pl.karol202.uranium.core.element.UElement
import pl.karol202.uranium.core.manager.RenderManager
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.URenderScope

object Swing

typealias SwingElement<P> = UElement<Swing, P>

typealias SwingNativeComponent<P> = UNativeComponent<Swing, P>

typealias SwingAbstractComponent<P> = UAbstractComponent<Swing, P>

typealias SwingStateful<S> = UStateful<S>

typealias SwingRenderScope = URenderScope<Swing>

typealias SwingRenderBuilder = URenderBuilder<Swing>

typealias SwingRenderManager<P> = RenderManager<Swing, P>

internal typealias Builder<T> = T.() -> T

internal fun renderScope() = object : URenderScope<Swing> { }
