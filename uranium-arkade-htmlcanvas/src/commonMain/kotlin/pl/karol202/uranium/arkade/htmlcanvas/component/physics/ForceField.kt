package pl.karol202.uranium.arkade.htmlcanvas.component.physics

import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderBuilder
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.physics.force.Force
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.render.render

fun ArkadeRenderScope.forceField(key: Any = AutoKey,
                                 force: Force,
                                 content: ArkadeRenderBuilder.() -> Unit) =
		physicsTransformer(key = key,
		                   transform = { it.withForce(force) }) {
			+ content.render()
		}
