package pl.karol202.uranium.arkade.canvas.values

enum class Direction(val vector: Vector)
{
	LEFT(Vector(-1.0, 0.0)),
	TOP(Vector(0.0, -1.0)),
	RIGHT(Vector(1.0, 0.0)),
	BOTTOM(Vector(0.0, 1.0))
}
