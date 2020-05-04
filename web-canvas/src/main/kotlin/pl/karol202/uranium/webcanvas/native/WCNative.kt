package pl.karol202.uranium.webcanvas.native

import pl.karol202.uranium.core.native.UNative
import pl.karol202.uranium.core.native.UNativeContainer
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.render.DrawContext
import pl.karol202.uranium.webcanvas.render.DrawOperation

val UNative<WC>.asWCNative get() = this as WCNative

interface WCNative : UNative<WC>
{
	fun draw(context: DrawContext)
}
