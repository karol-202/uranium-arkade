package pl.karol202.uranium.webcanvas.test

import kotlinx.html.dom.append
import kotlinx.html.js.canvas
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.BasicProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.assets.loadImage
import pl.karol202.uranium.webcanvas.component.base.WCAbstractComponent
import pl.karol202.uranium.webcanvas.component.primitives.circleFill
import pl.karol202.uranium.webcanvas.component.containers.translate
import pl.karol202.uranium.webcanvas.component.misc.mouseFollower
import pl.karol202.uranium.webcanvas.component.physics.collider.collider
import pl.karol202.uranium.webcanvas.component.physics.collider.collisionDomain
import pl.karol202.uranium.webcanvas.component.physics.forceField
import pl.karol202.uranium.webcanvas.component.physics.rigidbody
import pl.karol202.uranium.webcanvas.component.primitives.image
import pl.karol202.uranium.webcanvas.component.primitives.rectFill
import pl.karol202.uranium.webcanvas.component.ui.button
import pl.karol202.uranium.webcanvas.draw.startOnCanvas
import pl.karol202.uranium.webcanvas.physics.collider.CircleCollider
import pl.karol202.uranium.webcanvas.physics.collider.RectCollider
import pl.karol202.uranium.webcanvas.physics.force.GravitationalForce
import pl.karol202.uranium.webcanvas.physics.force.HomogenousForce
import pl.karol202.uranium.webcanvas.physics.force.RadialForce
import pl.karol202.uranium.webcanvas.values.Bounds
import pl.karol202.uranium.webcanvas.values.Color
import pl.karol202.uranium.webcanvas.values.Vector
import kotlin.browser.document

private val canvas = document.body!!.append.canvas { }

fun main() = startOnCanvas(canvas, renderInterval = 20, physicsInterval = 20) { app() }

class App(props: BasicProps) : WCAbstractComponent<BasicProps>(props)
{
	override fun URenderBuilder<WC>.render()
	{
		/*+ mouseFollower(minY = 400.0,
		                maxY = 400.0,
		                initialPosition = Vector(0.0, 400.0)) {
			+ rectFill(bounds = Bounds(-50.0, -50.0, 100.0, 100.0),
			           color = Color.named("blue"))
		}
		+ button(position = Vector(500.0, 100.0),
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

			+ forceField(force = GravitationalForce(HomogenousForce(Vector(0.0, 50.0)))) {
				+ rigidbody(initialPosition = Vector(0.0, 0.0),
				            initialVelocity = Vector(100.0, 0.0),
				            mass = 1.0,
				            collider = CircleCollider(Vector.ZERO, 30.0),
				            onCollision = { bounce(it.selfNormal, 0.5) }) {
					+ image(image = loadImage("assets/ball.png"),
					        drawBounds = Bounds(x = -30.0, y = -30.0,
					                            width = 60.0, height = 60.0))
				}
				+ translate(vector = Vector(100.0, 700.0)) {
					val bounds = Bounds(0.0, 0.0, 1000.0, 40.0)
					+ collider(collider = RectCollider(bounds))
					+ rectFill(bounds = bounds,
					           color = Color.named("red"))
				}
			}
		}
	}
}

fun WCRenderScope.app(key: Any = AutoKey) = component(::App, BasicProps(key))
