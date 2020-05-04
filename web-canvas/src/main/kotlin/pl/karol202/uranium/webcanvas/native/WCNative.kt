package pl.karol202.uranium.webcanvas.native

import pl.karol202.uranium.core.native.UNative
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.draw.DrawContext

val UNative<WC>.asWCNative get() = this as WCNative

interface WCNative : UNative<WC>
{
	fun draw(context: DrawContext)
}
