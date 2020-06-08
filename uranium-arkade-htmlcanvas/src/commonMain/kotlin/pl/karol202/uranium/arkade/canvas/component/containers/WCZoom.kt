package pl.karol202.uranium.arkade.canvas.component.containers

import pl.karol202.uranium.arkade.canvas.WCRenderBuilder
import pl.karol202.uranium.arkade.canvas.WCRenderScope
import pl.karol202.uranium.arkade.canvas.values.Bounds
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.render.render

fun WCRenderScope.zoom(key: Any = AutoKey,
                       area: Bounds,
                       content: WCRenderBuilder.() -> Unit) =
		translate(key = key,
		          vector = area.start) {
			+ scale(vector = area.size) {
				+ content.render()
			}
		}
