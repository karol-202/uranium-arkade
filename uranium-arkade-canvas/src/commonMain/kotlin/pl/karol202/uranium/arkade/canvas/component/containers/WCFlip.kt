package pl.karol202.uranium.arkade.canvas.component.containers

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.arkade.canvas.WCRenderBuilder
import pl.karol202.uranium.arkade.canvas.WCRenderScope
import pl.karol202.uranium.arkade.canvas.values.Vector

fun WCRenderScope.flip(key: Any = AutoKey,
                       size: Vector? = null, // If specified, content is translated accordingly
                       horizontal: Boolean = false,
                       vertical: Boolean = false,
                       content: WCRenderBuilder.() -> Unit) =
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

private fun WCRenderScope.flipScale(key: Any = AutoKey,
                                    horizontal: Boolean,
                                    vertical: Boolean,
                                    content: WCRenderBuilder.() -> Unit) =
		scale(key = key,
		      vector = Vector(if(horizontal) -1.0 else 1.0,
		                      if(vertical) -1.0 else 1.0)) {
			+ content.render()
		}

