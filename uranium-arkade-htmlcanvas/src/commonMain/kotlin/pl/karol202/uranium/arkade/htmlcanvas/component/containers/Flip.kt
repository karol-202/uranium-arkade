package pl.karol202.uranium.arkade.htmlcanvas.component.containers

import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderBuilder
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.values.Vector
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.render.render

fun ArkadeRenderScope.flip(key: Any = AutoKey,
                           size: Vector? = null, // If specified, content is translated accordingly
                           horizontal: Boolean = false,
                           vertical: Boolean = false,
                           content: ArkadeRenderBuilder.() -> Unit) =
		if(size != null)
			translate(key = key,
			          vector = Vector(x = if(horizontal) size.x else 0.0,
			                          y = if(vertical) size.y else 0.0)) {
				+ flipScale(horizontal = horizontal,
				            vertical = vertical,
				            content = content)
			}
		else
			flipScale(key = key,
			          horizontal = horizontal,
			          vertical = vertical,
			          content = content)

private fun ArkadeRenderScope.flipScale(key: Any = AutoKey,
                                        horizontal: Boolean,
                                        vertical: Boolean,
                                        content: ArkadeRenderBuilder.() -> Unit) =
		scale(key = key,
		      vector = Vector(if(horizontal) -1.0 else 1.0,
		                      if(vertical) -1.0 else 1.0)) {
			+ content.render()
		}

