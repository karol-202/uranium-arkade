package pl.karol202.uranium.arkade.htmlcanvas.component.draw

import pl.karol202.uranium.arkade.htmlcanvas.Arkade
import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.component.base.ArkadeAbstractNativeLeafComponent
import pl.karol202.uranium.arkade.htmlcanvas.draw.DrawContext
import pl.karol202.uranium.arkade.htmlcanvas.draw.DrawOperation
import pl.karol202.uranium.arkade.htmlcanvas.native.ArkadeDrawNativeLeaf
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.core.common.UProps
import pl.karol202.uranium.core.element.component
import pl.karol202.uranium.core.native.UNative

class DrawComponentArkade(props: Props) : ArkadeAbstractNativeLeafComponent<DrawComponentArkade.Props>(props)
{
	data class Props(override val key: Any,
	                 val drawOperation: DrawOperation) : UProps

	override val native: UNative<Arkade> = ArkadeDrawNativeLeaf { draw() }

	private fun DrawContext.draw() = props.drawOperation(this)
}

fun ArkadeRenderScope.drawComponent(key: Any = AutoKey,
                                    drawOperation: DrawOperation) =
		component(::DrawComponentArkade, DrawComponentArkade.Props(key, drawOperation))
