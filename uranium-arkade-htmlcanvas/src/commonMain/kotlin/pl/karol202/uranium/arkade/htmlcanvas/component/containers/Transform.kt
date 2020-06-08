package pl.karol202.uranium.arkade.htmlcanvas.component.containers

import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderBuilder
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.component.draw.drawContainer
import pl.karol202.uranium.arkade.htmlcanvas.component.event.eventTransformer
import pl.karol202.uranium.arkade.htmlcanvas.component.physics.collider.colliderTransformer
import pl.karol202.uranium.arkade.htmlcanvas.component.physics.physicsTransformer
import pl.karol202.uranium.arkade.htmlcanvas.draw.DrawOperation
import pl.karol202.uranium.arkade.htmlcanvas.physics.PhysicsContext
import pl.karol202.uranium.arkade.htmlcanvas.physics.collider.Collider
import pl.karol202.uranium.arkade.htmlcanvas.values.InputEvent
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.render.render

fun ArkadeRenderScope.fullTransform(key: Any = AutoKey,
                                    beforeDraw: DrawOperation,
                                    afterDraw: DrawOperation,
                                    transformEvent: (InputEvent) -> InputEvent,
                                    transformPhysics: (PhysicsContext) -> PhysicsContext,
                                    transformCollider: (Collider) -> Collider,
                                    content: ArkadeRenderBuilder.() -> Unit) =
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
