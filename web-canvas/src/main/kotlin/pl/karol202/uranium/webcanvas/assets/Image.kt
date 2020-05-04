package pl.karol202.uranium.webcanvas.assets

import org.w3c.dom.Image

fun loadImage(src: String) = Image().apply { this.src = src }
