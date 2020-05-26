package pl.karol202.uranium.webcanvas.component.ui

import pl.karol202.uranium.core.common.*
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.WCElement
import pl.karol202.uranium.webcanvas.WCRenderBuilder
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.base.WCAbstractComponent
import pl.karol202.uranium.webcanvas.component.event.eventHandler
import pl.karol202.uranium.webcanvas.component.primitives.image
import pl.karol202.uranium.webcanvas.values.Bounds
import pl.karol202.uranium.webcanvas.input.InputEvent
import pl.karol202.uranium.webcanvas.input.InputEvent.Mouse.Type
import pl.karol202.uranium.webcanvas.values.Vector

class WCButton(props: Props) : WCAbstractComponent<WCButton.Props>(props),
                               UStateful<WCButton.State>
{
	data class Props(override val key: Any = AutoKey,
	                 val size: Vector,
	                 val idleContent: List<WCElement<*>>,
	                 val hoverContent: List<WCElement<*>>,
	                 val clickContent: List<WCElement<*>>,
	                 val onClick: () -> Unit) : UProps

	data class State(val status: ButtonStatus = ButtonStatus.IDLE) : UState

	enum class ButtonStatus
	{
		IDLE, HOVER, CLICK
	}

	override var state by state(State())

	private val eventBounds get() = Bounds(size = props.size)
	private val content get() = when(state.status)
	{
		ButtonStatus.IDLE -> props.idleContent
		ButtonStatus.HOVER -> props.hoverContent
		ButtonStatus.CLICK -> props.clickContent
	}

	override fun URenderBuilder<WC>.render()
	{
		+ eventHandler(mouseListener = { handleEvent(it) })
		+ content
	}

	private fun handleEvent(event: InputEvent.Mouse)
	{
		fun getNonClickStatus(event: InputEvent.Mouse) =
				if(event.location in eventBounds) ButtonStatus.HOVER else ButtonStatus.IDLE

		when
		{
			event.type == Type.MOVE && state.status != ButtonStatus.CLICK -> setStatus(getNonClickStatus(event))
			event.type == Type.DOWN && event.location in eventBounds -> setStatus(ButtonStatus.CLICK)
			event.type == Type.UP && state.status == ButtonStatus.CLICK -> {
				setStatus(getNonClickStatus(event))
				props.onClick()
			}
			event.type == Type.LEAVE && state.status == ButtonStatus.CLICK -> setStatus(ButtonStatus.IDLE)
		}
	}

	private fun setStatus(status: ButtonStatus) = setState { copy(status = status) }
}

fun WCRenderScope.button(key: Any = AutoKey,
                         size: Vector,
                         idleContent: WCRenderBuilder.() -> Unit,
                         hoverContent: WCRenderBuilder.() -> Unit = idleContent,
                         clickContent: WCRenderBuilder.() -> Unit = idleContent,
                         onClick: () -> Unit) =
		component(::WCButton,
		          WCButton.Props(key, size, idleContent.render(), hoverContent.render(), clickContent.render(), onClick))
