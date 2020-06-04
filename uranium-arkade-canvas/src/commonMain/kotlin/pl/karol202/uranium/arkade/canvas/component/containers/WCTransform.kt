package pl.karol202.uranium.arkade.canvas.component.containers

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.arkade.canvas.WCRenderBuilder
import pl.karol202.uranium.arkade.canvas.WCRenderScope
import pl.karol202.uranium.arkade.canvas.component.draw.drawContainer
import pl.karol202.uranium.arkade.canvas.component.event.eventTransformer
import pl.karol202.uranium.arkade.canvas.component.physics.collider.colliderTransformer
import pl.karol202.uranium.arkade.canvas.component.physics.physicsTransformer
import pl.karol202.uranium.arkade.canvas.draw.DrawOperation
import pl.karol202.uranium.arkade.canvas.physics.PhysicsContext
import pl.karol202.uranium.arkade.canvas.physics.collider.Collider
import pl.karol202.uranium.arkade.canvas.values.InputEvent

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
