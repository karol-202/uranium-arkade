package pl.karol202.uranium.webcanvas.component.containers

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.webcanvas.WCRenderBuilder
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.values.Bounds

fun WCRenderScope.zoom(key: Any = AutoKey,
                       area: Bounds,
                       content: WCRenderBuilder.() -> Unit) =
		translate(key = key,
		          vector = area.start) {
			+ scale(vector = area.size) {
				+ content.render()
			}
		}
