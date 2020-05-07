package pl.karol202.uranium.webcanvas

import pl.karol202.uranium.core.element.UElement
import pl.karol202.uranium.core.manager.RenderManager
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.URenderScope

object WC

typealias WCElement<P> = UElement<WC, P>

typealias WCRenderScope = URenderScope<WC>

typealias WCRenderBuilder = URenderBuilder<WC>

typealias WCRenderManager<P> = RenderManager<WC, P>
