package pl.karol202.uranium.core.schedule

private const val MAX_BUFFER_SIZE = 64

internal expect fun queueRenderScheduler(queueSize: Int = MAX_BUFFER_SIZE): RenderScheduler
