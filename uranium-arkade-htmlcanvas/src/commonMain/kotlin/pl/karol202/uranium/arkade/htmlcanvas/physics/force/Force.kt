package pl.karol202.uranium.arkade.htmlcanvas.physics.force

import pl.karol202.uranium.arkade.htmlcanvas.physics.PhysicsBody
import pl.karol202.uranium.arkade.htmlcanvas.values.Vector

interface Force
{
	fun getForceAt(body: PhysicsBody): Vector

	fun translate(vector: Vector): Force

	fun scale(vector: Vector): Force
}
