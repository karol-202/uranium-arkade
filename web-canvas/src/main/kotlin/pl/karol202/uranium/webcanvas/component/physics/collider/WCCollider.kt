package pl.karol202.uranium.webcanvas.component.physics.collider

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.render.URenderBuilder
import pl.karol202.uranium.core.render.render
import pl.karol202.uranium.webcanvas.WC
import pl.karol202.uranium.webcanvas.WCRenderBuilder
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.base.WCAbstractComponent
import pl.karol202.uranium.webcanvas.component.physics.physicsTransformer
import pl.karol202.uranium.webcanvas.physics.PhysicsContext
import pl.karol202.uranium.webcanvas.physics.collider.Collider
import pl.karol202.uranium.webcanvas.physics.force.Force

class WCCollider(props: Props) : WCAbstractComponent<WCCollider.Props>(props)
{
	data class Props(override val key: Any = AutoKey,
	                 val collider: Collider) : UProps

	override fun URenderBuilder<WC>.render() {
		+ colliderProvider { props.collider }
	}
}

fun WCRenderScope.collider(key: Any = AutoKey,
                           collider: Collider) =
		component(::WCCollider, WCCollider.Props(key, collider))
