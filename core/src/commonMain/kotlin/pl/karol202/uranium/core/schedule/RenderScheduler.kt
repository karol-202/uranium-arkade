package pl.karol202.uranium.core.schedule

interface RenderScheduler<N>
{
	fun submit(function: () -> Unit)
}
