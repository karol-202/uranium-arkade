package pl.karol202.uranium.webcanvas.component.primitives

import org.w3c.dom.CanvasImageSource
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.URenderScope
import pl.karol202.uranium.webcanvas.*
import pl.karol202.uranium.webcanvas.component.base.WCAbstractComponent
import pl.karol202.uranium.webcanvas.component.draw.drawComponent
import pl.karol202.uranium.webcanvas.values.Bounds

fun WCRenderScope.image(key: Any = AutoKey,
                        image: CanvasImageSource,
                        drawBounds: Bounds,
                        clipBounds: Bounds? = null) =
		drawComponent(key = key) {
			if(clipBounds == null) drawImage(image, drawBounds.x, drawBounds.y, drawBounds.width, drawBounds.height)
			else drawImage(image, clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height,
			               drawBounds.x, drawBounds.y, drawBounds.width, drawBounds.height)
		}
