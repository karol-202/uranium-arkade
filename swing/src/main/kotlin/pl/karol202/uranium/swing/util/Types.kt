package pl.karol202.uranium.swing.util

import pl.karol202.uranium.core.component.AbstractComponent
import pl.karol202.uranium.core.component.StatefulComponent
import pl.karol202.uranium.core.tree.ComponentContext
import pl.karol202.uranium.core.context.UContext
import pl.karol202.uranium.core.element.UElement
import pl.karol202.uranium.core.render.RenderBuilder
import pl.karol202.uranium.core.render.RenderBuilderBase
import pl.karol202.uranium.core.render.RenderScope
import pl.karol202.uranium.core.schedule.BlockingRenderScheduler
import pl.karol202.uranium.core.schedule.SuspendRenderScheduler
import pl.karol202.uranium.core.tree.TreeNode
import pl.karol202.uranium.swing.SwingNativeWrapper
import java.awt.Container
import javax.swing.JComponent

typealias SwingNative = JComponent

typealias SwingContainer = Container

typealias SwingElement<P> = UElement<SwingNativeWrapper, P>

typealias SwingAbstractComponent<P> = AbstractComponent<SwingNativeWrapper, P>

typealias SwingStatefulComponent<P, S> = StatefulComponent<SwingNativeWrapper, P, S>

typealias SwingRenderScope = RenderScope<SwingNativeWrapper>

object SwingEmptyRenderScope : RenderScope<SwingNativeWrapper>

typealias SwingRenderBuilderBase = RenderBuilderBase<SwingNativeWrapper>

typealias SwingRenderBuilder = RenderBuilder<SwingNativeWrapper>

typealias SwingContext = UContext<SwingNativeWrapper>

typealias SwingInvalidateableContext = ComponentContext<SwingNativeWrapper>

typealias SwingTreeNode<P> = TreeNode<SwingNativeWrapper, P>

typealias SwingSuspendRenderScheduler = SuspendRenderScheduler<SwingNativeWrapper>

typealias SwingBlockingRenderScheduler = BlockingRenderScheduler<SwingNativeWrapper>

internal typealias Builder<T> = T.() -> T
