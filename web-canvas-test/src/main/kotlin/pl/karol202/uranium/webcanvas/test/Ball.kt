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
import pl.karol202.uranium.webcanvas.physics.PhysicsBody
import pl.karol202.uranium.webcanvas.physics.PhysicsContext
import pl.karol202.uranium.webcanvas.values.Bounds
import pl.karol202.uranium.webcanvas.values.Vector
import pl.karol202.uranium.webcanvas.values.average

class Ball(props: Props) : WCAbstractComponent<Ball.Props>(props),
                           UStateful<Ball.State>
{
	data class Props(override val key: Any,
	                 val image: CanvasImageSource,
	                 val initialPosition: Vector,
	                 val initialVelocity: Vector,
	                 val size: Vector,
	                 val mass: Double) : UProps

	data class State(val position: Vector,
	                 val velocity: Vector) : UState

	override var state by state(State(props.initialPosition, props.initialVelocity))

	private val bounds get() = Bounds(x = -props.size.x / 2, y = -props.size.y / 2,
	                                  width = props.size.x, height = props.size.y)
	private val body get() = PhysicsBody(state.position, props.mass)

	override fun WCRenderBuilder.render()
	{
		+ physicsPerformer { performPhysics() }
		+ translate(vector = state.position) {
			+ image(image = props.image, drawBounds = bounds)
		}
	}

	private fun PhysicsContext.performPhysics()
	{
		val force = getForceAt(body)
		val acceleration = force / props.mass ?: return
		val oldVelocity = state.velocity
		val newVelocity = oldVelocity + (acceleration * deltaTime.inSeconds)
		val offset = listOf(oldVelocity, newVelocity).average() * deltaTime.inSeconds
		setState { copy(position = position + offset, velocity = newVelocity) }
	}
}

fun WCRenderScope.ball(key: Any = AutoKey,
                       image: CanvasImageSource,
                       initialPosition: Vector,
                       initialVelocity: Vector,
                       size: Vector,
                       mass: Double) =
		component(::Ball, Ball.Props(key, image, initialPosition, initialVelocity, size, mass))
