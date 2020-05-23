package pl.karol202.uranium.webcanvas.component.containers

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.webcanvas.*
import pl.karol202.uranium.webcanvas.component.base.WCAbstractComponent
import pl.karol202.uranium.webcanvas.component.draw.drawContainer
import pl.karol202.uranium.webcanvas.component.event.eventTransformer
import pl.karol202.uranium.webcanvas.component.physics.collider.colliderTransformer
import pl.karol202.uranium.webcanvas.component.physics.physicsTransformer
import pl.karol202.uranium.webcanvas.draw.DrawContext
import pl.karol202.uranium.webcanvas.physics.PhysicsContext
import pl.karol202.uranium.webcanvas.physics.collider.Collider
import pl.karol202.uranium.webcanvas.values.InputEvent
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
