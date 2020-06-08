package pl.karol202.uranium.arkade.canvas.physics

import pl.karol202.uranium.arkade.canvas.values.Vector

data class PhysicsBody(val position: Vector,
                       val velocity: Vector,
                       val mass: Double)
