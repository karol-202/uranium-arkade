package pl.karol202.uranium.webcanvas.assets

expect class NativeImage
{
	companion object
	{
		fun load(src: String): NativeImage
	}
}
