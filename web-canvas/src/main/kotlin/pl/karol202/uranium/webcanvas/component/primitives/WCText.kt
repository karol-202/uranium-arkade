package pl.karol202.uranium.webcanvas.component.primitives

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.draw.drawComponent
import pl.karol202.uranium.webcanvas.values.FillStyle
import pl.karol202.uranium.webcanvas.values.Font
import pl.karol202.uranium.webcanvas.values.Vector

fun WCRenderScope.text(key: Any = AutoKey,
                       position: Vector,
                       text: String,
                       font: Font,
                       fillStyle: FillStyle) =
		drawComponent(key = key) {
			this.font = font.asText
			this.fillStyle = fillStyle.createNativeStyle(this)
			fillText(text, position.x, position.y)
		}
