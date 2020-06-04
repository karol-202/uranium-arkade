package pl.karol202.uranium.arkade.canvas.component.physics

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.arkade.canvas.WC
import pl.karol202.uranium.arkade.canvas.WCElement
import pl.karol202.uranium.arkade.canvas.WCRenderBuilder
import pl.karol202.uranium.arkade.canvas.WCRenderScope
import pl.karol202.uranium.arkade.canvas.component.base.WCAbstractComponent
import pl.karol202.uranium.arkade.canvas.physics.PhysicsContext
import pl.karol202.uranium.arkade.canvas.physics.force.Force

fun WCRenderScope.forceField(key: Any = AutoKey,
                             force: Force,
                             content: WCRenderBuilder.() -> Unit) =
		physicsTransformer(key = key,
		                   transform = { it.withForce(force) }) {
			+ content.render()
		}
