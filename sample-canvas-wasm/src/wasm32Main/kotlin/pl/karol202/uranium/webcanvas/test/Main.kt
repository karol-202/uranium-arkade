package pl.karol202.uranium.webcanvas.test

import pl.karol202.uranium.core.common.*
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.assets.Image
import pl.karol202.uranium.webcanvas.component.base.WCAbstractComponent
import pl.karol202.uranium.webcanvas.component.containers.flip
import pl.karol202.uranium.webcanvas.component.containers.scale
import pl.karol202.uranium.webcanvas.component.containers.translate
import pl.karol202.uranium.webcanvas.component.physics.WCRigidbody
import pl.karol202.uranium.webcanvas.component.physics.collider.collider
import pl.karol202.uranium.webcanvas.component.physics.collider.collisionDomain
import pl.karol202.uranium.webcanvas.component.physics.forceField
import pl.karol202.uranium.webcanvas.component.physics.rigidbody
import pl.karol202.uranium.webcanvas.component.primitives.image
import pl.karol202.uranium.webcanvas.component.primitives.rectFill
import pl.karol202.uranium.webcanvas.component.primitives.textFill
import pl.karol202.uranium.webcanvas.physics.collider.CircleCollider
import pl.karol202.uranium.webcanvas.physics.collider.RectCollider
import pl.karol202.uranium.webcanvas.physics.force.GravitationalForce
import pl.karol202.uranium.webcanvas.physics.force.HomogenousForce
import pl.karol202.uranium.webcanvas.startOnCanvas
import pl.karol202.uranium.webcanvas.values.*

fun main() = startOnCanvas("canvas", renderInterval = 20, physicsInterval = 20) { app() }

class App(props: BasicProps) : WCAbstractComponent<BasicProps>(props),
                               UStateful<App.State>
{
	data class State(val ballState: WCRigidbody.State) : UState

	override var state by state(State(WCRigidbody.State(position = Vector(0.0, 0.0),
	                                                    velocity = Vector(100.0, 0.0))))

	override fun URenderBuilder<WC>.render()
	{
		+ collisionDomain {
			+ forceField(force = GravitationalForce(HomogenousForce(Vector(0.0, 300.0)))) {
				+ rigidbody(state = state.ballState,
				            mass = 1.0,
				            collider = CircleCollider(circle = Circle(Vector.ZERO, 30.0)),
				            onStateChange = { setState { copy(ballState = it) } },
				            onCollision = { bounce(it.selfNormal, 0.5) }) {
					+ flip(horizontal = true) {
						+ image(image = Image.load("assets/ball.png"),
						        drawBounds = Bounds(x = -30.0, y = -30.0,
						                            width = 60.0, height = 60.0))
					}
				}
				+ scale(vector = Vector(0.5, 0.5)) {
					+ translate(vector = Vector(100.0, 700.0)) {
						val bounds = Bounds(0.0, 0.0, 1000.0, 40.0)
						+ collider(collider = RectCollider(bounds))
						+ rectFill(bounds = bounds,
						           fillStyle = Color.raw("red"))
						+ mouseFollower(minY = 400.0,
						                maxY = 400.0,
						                initialPosition = Vector(0.0, 400.0)) {
							+ rectFill(bounds = Bounds(-50.0, -50.0, 100.0, 100.0),
							           fillStyle = Gradient.Radial.simple(center = Vector(0.0, 0.0),
							                                              radius = 50.0,
							                                              centerColor = Color.raw("orange"),
							                                              radiusColor = Color.raw("green")))
						}
					}
				}
			}
		}
		+ textFill(position = Vector(x = 100.0, y = 100.0),
		           text = "Hell'o world!",
		           font = Font.create(20, "Times New Roman", "monospace"),
		           fillStyle = Color.raw("green"))
	}
}

fun WCRenderScope.app(key: Any = AutoKey) = component(::App, BasicProps(key))
