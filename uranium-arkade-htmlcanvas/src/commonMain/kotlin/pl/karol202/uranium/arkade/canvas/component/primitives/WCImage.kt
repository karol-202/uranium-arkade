package pl.karol202.uranium.arkade.canvas.component.primitives

import pl.karol202.uranium.arkade.canvas.WCRenderScope
import pl.karol202.uranium.arkade.canvas.assets.Image
import pl.karol202.uranium.arkade.canvas.component.draw.drawComponent
import pl.karol202.uranium.arkade.canvas.values.Bounds
import pl.karol202.uranium.core.common.AutoKey

fun WCRenderScope.image(key: Any = AutoKey,
                        image: Image,
                        drawBounds: Bounds,
                        clipBounds: Bounds? = null) =
		drawComponent(key = key) {
			if(clipBounds == null) drawImage(image, drawBounds.start, drawBounds.size)
			else drawImage(image, clipBounds.start, clipBounds.size, drawBounds.start, drawBounds.size)
		}
