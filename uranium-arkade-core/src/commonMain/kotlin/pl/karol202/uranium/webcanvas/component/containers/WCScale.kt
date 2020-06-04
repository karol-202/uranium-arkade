package pl.karol202.uranium.webcanvas.component.containers

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.webcanvas.*
import pl.karol202.uranium.webcanvas.values.InputEvent
import pl.karol202.uranium.webcanvas.values.Vector
import pl.karol202.uranium.webcanvas.values.div

fun WCRenderScope.scale(key: Any = AutoKey,
                        vector: Vector,
                        content: WCRenderBuilder.() -> Unit) =
		fullTransform(key = key,
		              beforeDraw = {
			              save()
			              scale(vector)
		              },
		              afterDraw = { restore() },
		              transformEvent = { when(it)
		              {
			              is InputEvent.Mouse -> it.withLocation(it.location / vector ?: Vector.ZERO)
			              is InputEvent.Key -> it
		              } },
		              transformPhysics = {
			              val scale = 1.0 / vector
			              if(scale != null) it.scale(scale) else it.noForces().noColliders()
		              },
		              transformCollider = { it.scale(vector) }) {
			+ content.render()
		}
