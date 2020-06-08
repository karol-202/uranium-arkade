package pl.karol202.uranium.arkade.canvas.physics.force

import pl.karol202.uranium.arkade.canvas.physics.PhysicsBody
import pl.karol202.uranium.arkade.canvas.values.Vector

interface Force
{
	fun getForceAt(body: PhysicsBody): Vector

	fun translate(vector: Vector): Force

	fun scale(vector: Vector): Force
}
