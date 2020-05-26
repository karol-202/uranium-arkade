package pl.karol202.uranium.webcanvas.component.containers

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.webcanvas.*
import pl.karol202.uranium.webcanvas.input.InputEvent
import pl.karol202.uranium.webcanvas.values.Vector

fun WCRenderScope.translate(key: Any = AutoKey,
                            vector: Vector,
                            content: WCRenderBuilder.() -> Unit) =
		fullTransform(key = key,
		              beforeDraw = {
			              save()
			              translate(vector.x, vector.y)
		              },
		              afterDraw = { restore() },
		              transformEvent = { when(it)
		              {
			              is InputEvent.Mouse -> it.withLocation(it.location - vector)
			              is InputEvent.Key -> it
		              } },
		              transformPhysics = { it.translate(-vector) },
		              transformCollider = { it.translate(vector) }) {
			+ content.render()
		}
