package pl.karol202.uranium.arkade.htmlcanvas

import pl.karol202.uranium.core.element.UElement
import pl.karol202.uranium.core.manager.RenderManager
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.URenderScope

object Arkade

typealias ArkadeElement<P> = UElement<Arkade, P>

typealias ArkadeRenderScope = URenderScope<Arkade>

typealias ArkadeRenderBuilder = URenderBuilder<Arkade>

typealias ArkadeRenderManager<P> = RenderManager<Arkade, P>
