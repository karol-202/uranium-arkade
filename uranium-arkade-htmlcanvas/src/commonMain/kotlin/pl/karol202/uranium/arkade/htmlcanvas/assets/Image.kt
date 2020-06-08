package pl.karol202.uranium.arkade.htmlcanvas.assets

import pl.karol202.uranium.arkade.htmlcanvas.dom.assets.NativeImage

class Image(internal val native: NativeImage)
{
	companion object
	{
		fun load(src: String) = Image(NativeImage.load(src))
	}
}
