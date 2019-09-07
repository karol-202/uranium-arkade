package pl.karol202.uranium.swing

import pl.karol202.uranium.core.component.AbstractComponent
import pl.karol202.uranium.core.component.StatefulComponent
import pl.karol202.uranium.core.component.UBuilder
import pl.karol202.uranium.core.context.InvalidateableContext
import pl.karol202.uranium.core.context.UContext
import java.awt.Component

typealias SwingNative = Component

typealias SwingComponent<P> = AbstractComponent<SwingNative, P>

typealias StatefulSwingComponent<P, S> = StatefulComponent<SwingNative, P, S>

typealias SwingBuilder = UBuilder<SwingNative>

typealias SwingContext = UContext<SwingNative>

typealias InvalidateableSwingContext = InvalidateableContext<SwingNative>
