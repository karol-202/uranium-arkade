package pl.karol202.uranium.core.context

import pl.karol202.uranium.core.common.Invalidateable

interface InvalidateableContext<N> : Context<N>, Invalidateable
