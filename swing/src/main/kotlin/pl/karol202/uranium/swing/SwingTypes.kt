package pl.karol202.uranium.swing

import pl.karol202.uranium.core.common.ChildrenProps
import pl.karol202.uranium.core.component.AbstractComponent
import pl.karol202.uranium.core.component.StatefulComponent
import pl.karol202.uranium.core.component.UBuilder
import pl.karol202.uranium.core.context.InvalidateableContext
import pl.karol202.uranium.core.context.UContext
import pl.karol202.uranium.core.element.UElement
import pl.karol202.uranium.core.internal.Renderer
import javax.swing.JComponent
import javax.swing.JPanel

typealias SwingNative = JComponent

typealias SwingContainer = JPanel

typealias SwingChildrenProps = ChildrenProps<SwingNative>

typealias SwingElement<P> = UElement<SwingNative, P>

typealias SwingComponent<P> = AbstractComponent<SwingNative, P>

typealias StatefulSwingComponent<P, S> = StatefulComponent<SwingNative, P, S>

typealias SwingBuilder = UBuilder<SwingNative>

typealias SwingContext = UContext<SwingNative>

typealias InvalidateableSwingContext = InvalidateableContext<SwingNative>

typealias SwingRenderer = Renderer<SwingNative>
