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

class WCImage(props: Props) : WCAbstractComponent<WCImage.Props>(props)
{
	data class Props(override val key: Any,
	                 val image: CanvasImageSource,
	                 val drawBounds: Bounds,
	                 val clipBounds: Bounds?) : UProps

	private val bounds get() = props.drawBounds
	private val clip get() = props.clipBounds

	override fun URenderBuilder<WC>.render()
	{
		+ drawComponent {
			val clip = clip
			if(clip == null) drawImage(props.image, bounds.x, bounds.y, bounds.width, bounds.height)
			else drawImage(props.image, clip.x, clip.y, clip.width, clip.height, bounds.x, bounds.y, bounds.width, bounds.height)
		}
	}
}

fun WCRenderScope.image(key: Any = AutoKey,
                        image: CanvasImageSource,
                        drawBounds: Bounds,
                        clipBounds: Bounds? = null) =
		component(::WCImage, WCImage.Props(key, image, drawBounds, clipBounds))
