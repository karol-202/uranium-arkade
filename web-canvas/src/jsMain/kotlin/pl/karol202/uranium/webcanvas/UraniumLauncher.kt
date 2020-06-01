package pl.karol202.uranium.webcanvas

import pl.karol202.uranium.core.manager.queueRenderScheduler

actual fun createRenderScheduler() = queueRenderScheduler()
