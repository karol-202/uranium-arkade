package pl.karol202.uranium.core.schedule

internal actual fun queueRenderScheduler(queueSize: Int): RenderScheduler =
		QueueRenderScheduler()

internal class QueueRenderScheduler : RenderScheduler
{
	init
	{
		println("TODO Start queue scheduler")
	}

	override fun submit(function: () -> Unit) = println("TODO Submit") // TODO
}
