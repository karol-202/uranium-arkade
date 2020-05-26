package pl.karol202.uranium.webcanvas.values

import pl.karol202.uranium.webcanvas.draw.DrawContext

infix fun Color.at(position: Double) = Gradient.Step(position, this)

sealed class Gradient : FillStyle
{
	data class Linear(val start: Vector,
	                  val end: Vector,
	                  override val steps: List<Step>) : Gradient()
	{
		companion object
		{
			fun create(start: Vector, end: Vector, vararg steps: Step) =
					Linear(start, end, steps.toList())

			fun simple(start: Vector, startColor: Color, end: Vector, endColor: Color) =
					create(start, end, startColor at 0.0, endColor at 1.0)
		}

		override fun createEmptyGradient(context: DrawContext) = context.createLinearGradient(start.x, start.y, end.x, end.y)
	}

	data class Radial(val startCircle: Circle,
	                  val endCircle: Circle,
	                  override val steps: List<Step>) : Gradient()
	{
		companion object
		{
			fun create(startCircle: Circle, endCircle: Circle, vararg steps: Step) =
					Radial(startCircle, endCircle, steps.toList())

			fun simple(center: Vector, radius: Double, centerColor: Color, radiusColor: Color) =
					create(Circle(center, 0.0), Circle(center, radius), centerColor at 0.0, radiusColor at 1.0)
		}

		override fun createEmptyGradient(context: DrawContext) =
				context.createRadialGradient(startCircle.center.x, startCircle.center.y, startCircle.radius,
				                             endCircle.center.x, endCircle.center.y, endCircle.radius)
	}

	data class Step(val position: Double,
	                val color: Color)

	abstract val steps: List<Step>

	override fun createNativeStyle(context: DrawContext) = createEmptyGradient(context).apply {
		steps.forEach { (position, color) -> addColorStop(position, color.asText) }
	}.asNativeFillStyle

	protected abstract fun createEmptyGradient(context: DrawContext): NativeGradient
}
