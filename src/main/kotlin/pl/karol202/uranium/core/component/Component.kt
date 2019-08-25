package pl.karol202.uranium.core.component

import pl.karol202.uranium.core.common.Attachable
import pl.karol202.uranium.core.common.Detachable
import pl.karol202.uranium.core.common.Renderable
import pl.karol202.uranium.core.common.WithKey

interface Component : WithKey, Renderable, Attachable, Detachable
