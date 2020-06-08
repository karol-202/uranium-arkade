package pl.karol202.uranium.arkade.htmlcanvas.component.ui

import pl.karol202.uranium.arkade.htmlcanvas.Arkade
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeElement
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderBuilder
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.component.base.AbstractComponent
import pl.karol202.uranium.arkade.htmlcanvas.component.event.eventHandler
import pl.karol202.uranium.arkade.htmlcanvas.values.Bounds
import pl.karol202.uranium.arkade.htmlcanvas.values.InputEvent
import pl.karol202.uranium.arkade.htmlcanvas.values.InputEvent.Mouse.Type
import pl.karol202.uranium.arkade.htmlcanvas.values.Vector
import pl.karol202.uranium.core.common.*
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.render

class Button(props: Props) : AbstractComponent<Button.Props>(props),
                             UStateful<Button.State>
{
	data class Props(override val key: Any = AutoKey,
	                 val size: Vector,
	                 val idleContent: List<ArkadeElement<*>>,
	                 val hoverContent: List<ArkadeElement<*>>,
	                 val clickContent: List<ArkadeElement<*>>,
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

	override fun URenderBuilder<Arkade>.render()
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

fun ArkadeRenderScope.button(key: Any = AutoKey,
                             size: Vector,
                             idleContent: ArkadeRenderBuilder.() -> Unit,
                             hoverContent: ArkadeRenderBuilder.() -> Unit = idleContent,
                             clickContent: ArkadeRenderBuilder.() -> Unit = idleContent,
                             onClick: () -> Unit) =
		component(::Button,
		          Button.Props(key, size, idleContent.render(), hoverContent.render(), clickContent.render(), onClick))
