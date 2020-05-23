package pl.karol202.uranium.webcanvas.component.physics

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.WCElement
import pl.karol202.uranium.webcanvas.WCRenderBuilder
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.base.WCAbstractComponent
import pl.karol202.uranium.webcanvas.physics.PhysicsContext
import pl.karol202.uranium.webcanvas.physics.force.Force

fun WCRenderScope.forceField(key: Any = AutoKey,
                             force: Force,
                             content: WCRenderBuilder.() -> Unit) =
		physicsTransformer(key = key,
		                   transform = { it.withForce(force) }) {
			+ content.render()
		}
