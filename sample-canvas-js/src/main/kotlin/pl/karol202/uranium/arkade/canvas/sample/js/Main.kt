package pl.karol202.uranium.arkade.canvas.sample.js

import pl.karol202.uranium.core.common.*
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.arkade.canvas.WC
import pl.karol202.uranium.arkade.canvas.WCRenderScope
import pl.karol202.uranium.arkade.canvas.assets.Image
import pl.karol202.uranium.arkade.canvas.component.base.WCAbstractComponent
import pl.karol202.uranium.arkade.canvas.component.containers.flip
import pl.karol202.uranium.arkade.canvas.component.containers.scale
import pl.karol202.uranium.arkade.canvas.component.containers.translate
import pl.karol202.uranium.arkade.canvas.component.physics.WCRigidbody
import pl.karol202.uranium.arkade.canvas.component.physics.collider.collider
import pl.karol202.uranium.arkade.canvas.component.physics.collider.collisionDomain
import pl.karol202.uranium.arkade.canvas.component.physics.forceField
import pl.karol202.uranium.arkade.canvas.component.physics.rigidbody
import pl.karol202.uranium.arkade.canvas.component.primitives.image
import pl.karol202.uranium.arkade.canvas.component.primitives.rectFill
import pl.karol202.uranium.arkade.canvas.component.primitives.textFill
import pl.karol202.uranium.arkade.canvas.physics.collider.CircleCollider
import pl.karol202.uranium.arkade.canvas.physics.collider.RectCollider
import pl.karol202.uranium.arkade.canvas.physics.force.GravitationalForce
import pl.karol202.uranium.arkade.canvas.physics.force.HomogenousForce
import pl.karol202.uranium.arkade.canvas.startOnCanvas
import pl.karol202.uranium.arkade.canvas.values.*

fun main() = startOnCanvas("canvas", renderInterval = 20, physicsInterval = 20) { app() }

class App(props: BasicProps) : WCAbstractComponent<BasicProps>(props),
                               UStateful<App.State>
{
	data class State(val ballState: WCRigidbody.State) : UState

	override var state by state(State(WCRigidbody.State(position = Vector(0.0, 0.0), velocity = Vector(100.0, 0.0))))

	override fun URenderBuilder<WC>.render()
	{
		/*+ button(position = Vector(500.0, 100.0),
		         size = Vector(100.0, 100.0),
		         idleImage = loadImage("assets/ball.png"),
		         hoverImage = loadImage("assets/ball_hover.png"),
		         clickImage = loadImage("assets/ball_click.png"),
		         onClick = { console.log("click") })*/

		+ collisionDomain {
			/*+ forceField(force = GravitationalForce(RadialForce(center = Vector(200.0, 250.0),
			                                                    intensity = 500.0,
			                                                    linearAttenuation = 0.1,
			                                                    quadraticAttenuation = 0.0))) {
				+ circleFill(center = Vector(200.0, 250.0),
				             radius = 10.0,
				             color = Color.named("black"))
				+ translate(vector = Vector(200.0, 0.0)) {
					+ rigidbody(initialPosition = Vector(0.0, 150.0),
					            initialVelocity = Vector(60.0, 0.0),
					            mass = 1.0,
					            collider = CircleCollider(Vector.ZERO, 30.0),
					            onCollision = { bounce(it.selfNormal) }) {
						+ image(image = loadImage("assets/ball.png"),
						        drawBounds = Bounds(x = -30.0, y = -30.0,
						                            width = 60.0, height = 60.0))
					}
				}
			}
			+ translate(vector = Vector(150.0, 350.0)) {
				val bounds = Bounds(0.0, 0.0, 100.0, 40.0)
				+ collider(collider = RectCollider(bounds))
				+ rectFill(bounds = bounds,
				           color = Color.named("red"))
			}*/

			//+ translate(vector = Vector(600.0, 0.0)) {
				//+ scale(vector = Vector(-0.5, 0.5)) {
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
				//}
			//}
		}
		+ textFill(position = Vector(x = 100.0, y = 100.0),
		           text = "Hell'o world!",
		           font = Font.create(20, "Times New Roman", "monospace"),
		           fillStyle = Color.raw("green"))
	}
}

fun WCRenderScope.app(key: Any = AutoKey) = component(::App, BasicProps(key))
