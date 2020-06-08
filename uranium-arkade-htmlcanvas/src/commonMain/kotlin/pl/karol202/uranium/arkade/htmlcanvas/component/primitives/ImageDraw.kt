package pl.karol202.uranium.arkade.htmlcanvas.component.primitives

import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.assets.Image
import pl.karol202.uranium.arkade.htmlcanvas.component.draw.drawComponent
import pl.karol202.uranium.arkade.htmlcanvas.values.Bounds
import pl.karol202.uranium.core.common.AutoKey

fun ArkadeRenderScope.imageDraw(key: Any = AutoKey,
                                image: Image,
                                drawBounds: Bounds,
                                clipBounds: Bounds? = null) =
		drawComponent(key = key) {
			if(clipBounds == null) drawImage(image, drawBounds.start, drawBounds.size)
			else drawImage(image, clipBounds.start, clipBounds.size, drawBounds.start, drawBounds.size)
		}
