package pl.karol202.uranium.arkade.htmlcanvas.component.primitives

import pl.karol202.uranium.arkade.htmlcanvas.ArkadeRenderScope
import pl.karol202.uranium.arkade.htmlcanvas.component.draw.drawComponent
import pl.karol202.uranium.arkade.htmlcanvas.values.*
import pl.karol202.uranium.core.common.AutoKey

fun ArkadeRenderScope.textFill(key: Any = AutoKey,
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
