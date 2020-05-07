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
import pl.karol202.uranium.webcanvas.component.containers.group
import pl.karol202.uranium.webcanvas.component.primitives.rectFill
import pl.karol202.uranium.webcanvas.component.containers.translate
import pl.karol202.uranium.webcanvas.component.misc.mouseFollower
import pl.karol202.uranium.webcanvas.component.physics.physicsPerformer
import pl.karol202.uranium.webcanvas.component.primitives.canvasClear
import pl.karol202.uranium.webcanvas.component.ui.button
import pl.karol202.uranium.webcanvas.draw.startOnCanvas
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
		+ translate(vector = Vector(200.0, 0.0)) {
			+ mouseFollower(minX = 0.0,
			                minY = 0.0,
			                maxY = 0.0) {
				+ rectFill(bounds = Bounds(-50.0, -50.0, 100.0, 100.0),
				           style = Color.named("blue"))
			}
			+ button(position = Vector(100.0, 200.0),
			         size = Vector(100.0, 100.0),
			         idleImage = loadImage("assets/ball.png"),
			         hoverImage = loadImage("assets/ball_hover.png"),
			         clickImage = loadImage("assets/ball_click.png"),
			         onClick = { console.log("click") })
		}
		+ ball(size = Vector(x = 60.0, y = 60.0), image = loadImage("assets/ball.png"))
	}
}

fun WCRenderScope.app(key: Any = AutoKey) = component(::App, BasicProps(key))
