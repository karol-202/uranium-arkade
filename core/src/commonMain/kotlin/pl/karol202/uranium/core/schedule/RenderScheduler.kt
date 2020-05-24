package pl.karol202.uranium.core.schedule

internal interface RenderScheduler
{
	fun submit(function: () -> Unit)
}
