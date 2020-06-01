package pl.karol202.uranium.webcanvas.component.primitives

import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.draw.drawComponent
import pl.karol202.uranium.webcanvas.values.*

fun WCRenderScope.textFill(key: Any = AutoKey,
                           position: Vector,
                           text: String,
                           font: Font,
                           fillStyle: FillStyle,
                           align: TextAlign = TextAlign.START,
                           baseline: TextBaseline = TextBaseline.ALPHABETIC,
                           direction: TextDirection = TextDirection.INHERIT) =
		drawComponent(key = key) {
			setFont(font)
			setFillStyle(fillStyle)
			setTextAlign(align)
			setTextBaseline(baseline)
			setDirection(direction)
			fillText(text, position)
		}
