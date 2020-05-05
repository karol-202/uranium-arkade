package pl.karol202.uranium.webcanvas.component.ui

import org.w3c.dom.CanvasImageSource
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.base.WCAbstractComponent
import pl.karol202.uranium.webcanvas.component.containers.translate
import pl.karol202.uranium.webcanvas.component.event.eventHandler
import pl.karol202.uranium.webcanvas.component.primitives.image
import pl.karol202.uranium.webcanvas.values.Bounds
import pl.karol202.uranium.webcanvas.values.Vector

class WCButton(props: Props) : WCAbstractComponent<WCButton.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 val position: Vector,
	                 val size: Vector,
	                 val image: CanvasImageSource) : UProps

	override fun WCRenderScope.render() =
			translate(vector = props.position) {
				+ eventHandler { console.log(it) }
				+ image(image = props.image, drawBounds = Bounds(size = props.size))
			}.asList()
}

fun WCRenderScope.button(key: Any = AutoKey,
                         position: Vector,
                         size: Vector,
                         image: CanvasImageSource) =
		component(::WCButton, WCButton.Props(key, position, size, image))
