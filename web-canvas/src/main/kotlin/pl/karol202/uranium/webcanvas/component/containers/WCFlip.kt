package pl.karol202.uranium.webcanvas.component.containers

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.webcanvas.WCRenderBuilder
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.values.Vector

fun WCRenderScope.flip(key: Any = AutoKey,
                       horizontal: Boolean = false,
                       vertical: Boolean = false,
                       content: WCRenderBuilder.() -> Unit) =
		scale(key = key,
		      vector = Vector(if(horizontal) -1.0 else 1.0,
		                      if(vertical) -1.0 else 1.0)) {
			+ content.render()
		}
