package pl.karol202.uranium.webcanvas.component.ui

import org.w3c.dom.CanvasImageSource
import pl.karol202.uranium.core.common.*
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.base.WCAbstractComponent
import pl.karol202.uranium.webcanvas.component.containers.translate
import pl.karol202.uranium.webcanvas.component.event.eventHandler
import pl.karol202.uranium.webcanvas.component.primitives.image
import pl.karol202.uranium.webcanvas.values.Bounds
import pl.karol202.uranium.webcanvas.values.InputEvent
import pl.karol202.uranium.webcanvas.values.Vector

class WCButton(props: Props) : WCAbstractComponent<WCButton.Props>(props),
                               UStateful<WCButton.State>
{
	data class Props(override val key: Any = AutoKey,
	                 val position: Vector,
	                 val size: Vector,
	                 val idleImage: CanvasImageSource,
	                 val hoverImage: CanvasImageSource,
	                 val clickImage: CanvasImageSource,
	                 val onClick: () -> Unit) : UProps

	data class State(val status: ButtonStatus = ButtonStatus.IDLE) : UState

	enum class ButtonStatus
	{
		IDLE, HOVER, CLICK
	}

	override var state by state(State())

	private val eventBounds get() = Bounds(size = props.size)
	private val image get() = when(state.status)
	{
		ButtonStatus.IDLE -> props.idleImage
		ButtonStatus.HOVER -> props.hoverImage
		ButtonStatus.CLICK -> props.clickImage
	}

	override fun URenderBuilder<WC>.render()
	{
		+ translate(vector = props.position) {
			+ eventHandler { handleEvent(it) }
			+ image(image = image, drawBounds = Bounds(size = props.size))
		}
	}

	private fun handleEvent(event: InputEvent)
	{
		when
		{
			event is InputEvent.Mouse.Down && event.location in eventBounds -> setStatus(ButtonStatus.CLICK)
			event is InputEvent.Mouse.Up && state.status == ButtonStatus.CLICK -> {
				setStatus(ButtonStatus.IDLE)
				props.onClick()
			}
			event is InputEvent.Mouse.Move && state.status != ButtonStatus.CLICK ->
				setStatus(if(event.location in eventBounds) ButtonStatus.HOVER else ButtonStatus.IDLE)
		}
	}

	private fun setStatus(status: ButtonStatus) = setState { copy(status = status) }
}

fun WCRenderScope.button(key: Any = AutoKey,
                         position: Vector,
                         size: Vector,
                         idleImage: CanvasImageSource,
                         hoverImage: CanvasImageSource,
                         clickImage: CanvasImageSource,
                         onClick: () -> Unit) =
		component(::WCButton, WCButton.Props(key, position, size, idleImage, hoverImage, clickImage, onClick))
