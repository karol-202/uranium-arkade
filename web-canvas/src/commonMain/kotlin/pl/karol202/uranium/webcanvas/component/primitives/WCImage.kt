package pl.karol202.uranium.webcanvas.component.primitives

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.draw.drawComponent
import pl.karol202.uranium.webcanvas.dom.assets.NativeImage
import pl.karol202.uranium.webcanvas.values.Bounds

fun WCRenderScope.image(key: Any = AutoKey,
                        image: NativeImage,
                        drawBounds: Bounds,
                        clipBounds: Bounds? = null) =
		drawComponent(key = key) {
			if(clipBounds == null) drawImage(image, drawBounds.x, drawBounds.y, drawBounds.width, drawBounds.height)
			else drawImage(image, clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height,
			               drawBounds.x, drawBounds.y, drawBounds.width, drawBounds.height)
		}
