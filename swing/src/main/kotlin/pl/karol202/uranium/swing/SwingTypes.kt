package pl.karol202.uranium.swing

import pl.karol202.uranium.core.component.AbstractComponent
import pl.karol202.uranium.core.component.StatefulComponent
import pl.karol202.uranium.core.context.InvalidateableContext
import pl.karol202.uranium.core.context.UContext
import pl.karol202.uranium.core.element.UElement
import pl.karol202.uranium.core.internal.Renderer
import pl.karol202.uranium.core.util.RenderBuilder
import java.awt.Container
import javax.swing.JComponent

typealias SwingNative = JComponent

typealias SwingContainer = Container

typealias SwingElement<P> = UElement<SwingNative, P>

typealias SwingAbstractComponent<P> = AbstractComponent<SwingNative, P>

typealias SwingStatefulComponent<P, S> = StatefulComponent<SwingNative, P, S>

typealias SwingRenderBuilder = RenderBuilder<SwingNative>

typealias SwingContext = UContext<SwingNative>

typealias InvalidateableSwingContext = InvalidateableContext<SwingNative>

typealias SwingRenderer = Renderer<SwingNative>
