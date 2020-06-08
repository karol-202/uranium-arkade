package pl.karol202.uranium.arkade.htmlcanvas.physics

import pl.karol202.uranium.arkade.htmlcanvas.values.Vector

data class PhysicsBody(val position: Vector,
                       val velocity: Vector,
                       val mass: Double)
