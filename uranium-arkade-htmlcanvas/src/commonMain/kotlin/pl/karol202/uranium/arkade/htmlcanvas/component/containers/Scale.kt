package pl.karol202.uranium.arkade.htmlcanvas.component.containers

import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderBuilder
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.values.InputEvent
import pl.karol202.uranium.arkade.htmlcanvas.values.Vector
import pl.karol202.uranium.arkade.htmlcanvas.values.div
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.render.render

fun ArkadeRenderScope.scale(key: Any = AutoKey,
                            vector: Vector,
                            content: ArkadeRenderBuilder.() -> Unit) =
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
