package pl.karol202.uranium.webcanvas.test

import org.w3c.dom.CanvasImageSource
import pl.karol202.uranium.core.common.*
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.webcanvas.WCRenderBuilder
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.base.WCAbstractComponent
import pl.karol202.uranium.webcanvas.component.containers.translate
import pl.karol202.uranium.webcanvas.component.physics.physicsPerformer
import pl.karol202.uranium.webcanvas.component.primitives.image
import pl.karol202.uranium.webcanvas.physics.PhysicsContext
import pl.karol202.uranium.webcanvas.values.Bounds
import pl.karol202.uranium.webcanvas.values.Vector

class Ball(props: Props) : WCAbstractComponent<Ball.Props>(props),
                           UStateful<Ball.State>
{
	data class Props(override val key: Any,
	                 val size: Vector,
	                 val image: CanvasImageSource) : UProps

	data class State(val position: Vector = Vector.ZERO) : UState

	override var state by state(State())

	private val bounds get() = Bounds(x = -props.size.x / 2, y = -props.size.y / 2,
	                                  width = props.size.x, height = props.size.y)

	override fun WCRenderBuilder.render()
	{
		+ translate(vector = state.position) {
			+ physicsPerformer { performPhysics() }
			+ image(image = props.image, drawBounds = bounds)
		}
	}

	private fun PhysicsContext.performPhysics()
	{
		val velocity = Vector(x = 10.0, y = 5.0)
		val offset = velocity * deltaTime.inSeconds
		setState { copy(position = position + offset) }
	}
}

fun WCRenderScope.ball(key: Any = AutoKey,
                       size: Vector,
                       image: CanvasImageSource) = component(::Ball, Ball.Props(key, size, image))
