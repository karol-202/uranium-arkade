package pl.karol202.uranium.arkade.canvas.component.physics

import pl.karol202.uranium.arkade.canvas.WCRenderBuilder
import pl.karol202.uranium.arkade.canvas.WCRenderScope
import pl.karol202.uranium.arkade.canvas.physics.force.Force
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.render.render

fun WCRenderScope.forceField(key: Any = AutoKey,
                             force: Force,
                             content: WCRenderBuilder.() -> Unit) =
		physicsTransformer(key = key,
		                   transform = { it.withForce(force) }) {
			+ content.render()
		}
