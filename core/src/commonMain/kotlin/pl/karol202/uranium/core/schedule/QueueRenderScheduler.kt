package pl.karol202.uranium.core.schedule

// Due to bug, making this constant private makes error "e: Compilation failed: No coordinates found for DeclarationId(id=2)"
// appear when compiling dependent modules to Kotlin/Native.
internal const val MAX_BUFFER_SIZE = 64

internal expect fun queueRenderScheduler(queueSize: Int = MAX_BUFFER_SIZE): RenderScheduler
