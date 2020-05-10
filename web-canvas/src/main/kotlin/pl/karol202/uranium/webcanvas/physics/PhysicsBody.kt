package pl.karol202.uranium.webcanvas.physics

import pl.karol202.uranium.webcanvas.values.Vector

data class PhysicsBody(val position: Vector,
                       val velocity: Vector,
                       val mass: Double)
