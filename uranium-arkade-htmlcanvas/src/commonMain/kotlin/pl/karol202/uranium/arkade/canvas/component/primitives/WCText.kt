package pl.karol202.uranium.arkade.canvas.component.primitives

import pl.karol202.uranium.arkade.canvas.WCRenderScope
import pl.karol202.uranium.arkade.canvas.component.draw.drawComponent
import pl.karol202.uranium.arkade.canvas.values.*
import pl.karol202.uranium.core.common.AutoKey

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
