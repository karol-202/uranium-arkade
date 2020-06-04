package pl.karol202.uranium.arkade.canvas.assets

import pl.karol202.uranium.arkade.canvas.dom.assets.NativeImage

class Image(internal val native: NativeImage)
{
	companion object
	{
		fun load(src: String) = Image(NativeImage.load(src))
	}
}
