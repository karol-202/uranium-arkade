package pl.karol202.uranium.webcanvas.physics.force

import pl.karol202.uranium.webcanvas.physics.PhysicsBody
import pl.karol202.uranium.webcanvas.values.Vector

interface Force
{
	fun getForceAt(body: PhysicsBody): Vector

	fun translate(vector: Vector): Force

	fun scale(vector: Vector): Force
}
