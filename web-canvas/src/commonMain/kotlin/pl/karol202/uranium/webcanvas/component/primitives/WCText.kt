package pl.karol202.uranium.webcanvas.component.primitives

import org.w3c.dom.*
import pl.karol202.uranium.core.common.AutoKey
import pl.karol202.uranium.webcanvas.WCRenderScope
import pl.karol202.uranium.webcanvas.component.draw.drawComponent
import pl.karol202.uranium.webcanvas.values.FillStyle
import pl.karol202.uranium.webcanvas.values.Font
import pl.karol202.uranium.webcanvas.values.Vector

enum class TextAlign(val native: CanvasTextAlign)
{
	START(CanvasTextAlign.START),
	LEFT(CanvasTextAlign.LEFT),
	CENTER(CanvasTextAlign.CENTER),
	RIGHT(CanvasTextAlign.RIGHT),
	END(CanvasTextAlign.END)
}

enum class TextBaseline(val native: CanvasTextBaseline)
{
	TOP(CanvasTextBaseline.TOP),
	MIDDLE(CanvasTextBaseline.MIDDLE),
	BOTTOM(CanvasTextBaseline.BOTTOM),
	ALPHABETIC(CanvasTextBaseline.ALPHABETIC),
	HANGING(CanvasTextBaseline.HANGING),
	IDEOGRAPHIC(CanvasTextBaseline.IDEOGRAPHIC)
}

enum class TextDirection(val native: CanvasDirection)
{
	LTR(CanvasDirection.LTR),
	RTL(CanvasDirection.RTL),
	INHERIT(CanvasDirection.INHERIT),
}

fun WCRenderScope.textFill(key: Any = AutoKey,
                           position: Vector,
                           text: String,
                           font: Font,
                           fillStyle: FillStyle,
                           align: TextAlign = TextAlign.START,
                           baseline: TextBaseline = TextBaseline.ALPHABETIC,
                           direction: TextDirection = TextDirection.INHERIT) =
		drawComponent(key = key) {
			this.font = font.asText
			this.fillStyle = fillStyle.createNativeStyle(this)
			this.textAlign = align.native
			this.textBaseline = baseline.native
			this.direction = direction.native
			fillText(text, position.x, position.y)
		}
