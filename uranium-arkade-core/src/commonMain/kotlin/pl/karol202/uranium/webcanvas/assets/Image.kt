package pl.karol202.uranium.webcanvas.assets

import pl.karol202.uranium.webcanvas.dom.assets.NativeImage

class Image(internal val native: NativeImage)
{
	companion object
	{
		fun load(src: String) = Image(NativeImage.load(src))
	}
}
