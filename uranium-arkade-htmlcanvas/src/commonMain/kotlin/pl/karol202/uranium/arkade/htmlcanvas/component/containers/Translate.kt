package pl.karol202.uranium.arkade.htmlcanvas.component.containers

import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderBuilder
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.values.InputEvent
import pl.karol202.uranium.arkade.htmlcanvas.values.Vector
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.render.render

fun ArkadeRenderScope.translate(key: Any = AutoKey,
                                vector: Vector,
                                content: ArkadeRenderBuilder.() -> Unit) =
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
