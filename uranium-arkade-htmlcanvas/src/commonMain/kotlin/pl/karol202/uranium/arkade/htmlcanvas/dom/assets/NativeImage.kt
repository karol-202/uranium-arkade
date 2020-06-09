package pl.karol202.uranium.arkade.htmlcanvas.dom.assets

internal expect class NativeImage
{
	companion object
	{
		fun load(src: String): NativeImage
	}
}
