package pl.karol202.uranium.arkade.htmlcanvas.component.containers

import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderBuilder
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.values.Bounds
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.render.render

fun ArkadeRenderScope.zoom(key: Any = AutoKey,
                           area: Bounds,
                           content: ArkadeRenderBuilder.() -> Unit) =
		translate(key = key,
		          vector = area.start) {
			+ scale(vector = area.size) {
				+ content.render()
			}
		}
