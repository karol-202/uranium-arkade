package pl.karol202.uranium.arkade.canvas.component.containers

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.arkade.canvas.*
import pl.karol202.uranium.arkade.canvas.values.InputEvent
import pl.karol202.uranium.arkade.canvas.values.Vector

fun WCRenderScope.translate(key: Any = AutoKey,
                            vector: Vector,
                            content: WCRenderBuilder.() -> Unit) =
		fullTransform(key = key,
		              beforeDraw = {
			              save()
			              translate(vector)
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
