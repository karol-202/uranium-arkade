package pl.karol202.uranium.arkade.canvas.physics

import pl.karol202.uranium.arkade.canvas.physics.collider.Collider
import pl.karol202.uranium.arkade.canvas.values.Vector

data class Collision(val selfCollider: Collider,
                     val otherCollider: Collider,
                     val penetration: Vector,
                     val selfNormal: Vector,
                     val otherNormal: Vector)
// Penetration vector relates to penetration of otherCollider into selfCollider,
// i.e. it's vector selfCollider should be translated by to resolve collision
{
	fun swapColliders() = copy(selfCollider = otherCollider,
	                           otherCollider = selfCollider,
	                           penetration = -penetration,
	                           selfNormal = otherNormal,
	                           otherNormal = selfNormal)
}
