package pl.karol202.uranium.webcanvas.test

import kotlinx.html.dom.append
import kotlinx.html.js.canvas
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.BasicProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderScope
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.WCAbstractAppComponent
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.rectFill
import pl.karol202.uranium.webcanvas.component.translate
import pl.karol202.uranium.webcanvas.render.CanvasRenderer
import pl.karol202.uranium.webcanvas.values.Bounds
import pl.karol202.uranium.webcanvas.values.Color
import pl.karol202.uranium.webcanvas.values.Vector
import kotlin.browser.document

private const val RENDER_INTERVAL = 20

private val canvas = document.body!!.append.canvas { }
private val context = canvas.context2d

fun main()
{
	canvas.fixBounds()
	CanvasRenderer(context, RENDER_INTERVAL) { app() }.start()
}

class App(props: BasicProps) : WCAbstractAppComponent<BasicProps>(props)
{
	override fun URenderScope<WC>.render() = translate(vector = Vector(200.0, 0.0)) {
		+ rectFill(bounds = Bounds(100.0, 100.0, 100.0, 100.0),
		           style = Color.named("blue"))
	}
}

fun WCRenderScope.app(key: Any = AutoKey) = component(::App, BasicProps(key))
