package pl.karol202.uranium.webcanvas.component.containers

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.webcanvas.WCRenderBuilder
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.draw.drawContainer
import pl.karol202.uranium.webcanvas.component.event.eventTransformer
import pl.karol202.uranium.webcanvas.component.physics.collider.colliderTransformer
import pl.karol202.uranium.webcanvas.component.physics.physicsTransformer
import pl.karol202.uranium.webcanvas.draw.DrawOperation
import pl.karol202.uranium.webcanvas.physics.PhysicsContext
import pl.karol202.uranium.webcanvas.physics.collider.Collider
import pl.karol202.uranium.webcanvas.values.InputEvent

fun WCRenderScope.fullTransform(key: Any = AutoKey,
                                beforeDraw: DrawOperation,
                                afterDraw: DrawOperation,
                                transformEvent: (InputEvent) -> InputEvent,
                                transformPhysics: (PhysicsContext) -> PhysicsContext,
                                transformCollider: (Collider) -> Collider,
                                content: WCRenderBuilder.() -> Unit) =
		drawContainer(key = key,
		              beforeDrawOperation = beforeDraw,
		              afterDrawOperation = afterDraw) {
			+ eventTransformer(transform = transformEvent) {
				+ physicsTransformer(transform = transformPhysics) {
					+ colliderTransformer(transform = transformCollider) {
						+ content.render()
					}
				}
			}
		}
