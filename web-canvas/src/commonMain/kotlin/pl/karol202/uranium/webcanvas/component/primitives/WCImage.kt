package pl.karol202.uranium.webcanvas.component.primitives

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.assets.Image
import pl.karol202.uranium.webcanvas.component.draw.drawComponent
import pl.karol202.uranium.webcanvas.values.Bounds

fun WCRenderScope.image(key: Any = AutoKey,
                        image: Image,
                        drawBounds: Bounds,
                        clipBounds: Bounds? = null) =
		drawComponent(key = key) {
			if(clipBounds == null) drawImage(image, drawBounds.start, drawBounds.size)
			else drawImage(image, clipBounds.start, clipBounds.size, drawBounds.start, drawBounds.size)
		}
