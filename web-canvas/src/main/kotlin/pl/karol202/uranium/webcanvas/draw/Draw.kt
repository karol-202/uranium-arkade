package pl.karol202.uranium.webcanvas.draw

import org.w3c.dom.CanvasRenderingContext2D

typealias DrawContext = CanvasRenderingContext2D

typealias DrawOperation = DrawContext.() -> Unit
