package pl.karol202.uranium.swing.util

import pl.karol202.uranium.core.component.AbstractComponent
import pl.karol202.uranium.core.component.StatefulComponent
import pl.karol202.uranium.core.element.UElement
import pl.karol202.uranium.core.render.RenderBuilder
import pl.karol202.uranium.core.render.RenderBuilderBase
import pl.karol202.uranium.core.render.RenderScope
import pl.karol202.uranium.core.schedule.BlockingRenderScheduler
import pl.karol202.uranium.core.schedule.SuspendRenderScheduler
import pl.karol202.uranium.core.tree.TreeNode

object Swing

typealias SwingElement<P> = UElement<Swing, P>

typealias SwingAbstractComponent<P> = AbstractComponent<Swing, P>

typealias SwingStatefulComponent<P, S> = StatefulComponent<Swing, P, S>

typealias SwingRenderScope = RenderScope<Swing>

object SwingEmptyRenderScope : RenderScope<Swing>

typealias SwingRenderBuilderBase = RenderBuilderBase<Swing>

typealias SwingRenderBuilder = RenderBuilder<Swing>

typealias SwingTreeNode<P> = TreeNode<Swing, P>

typealias SwingSuspendRenderScheduler = SuspendRenderScheduler<Swing>

typealias SwingBlockingRenderScheduler = BlockingRenderScheduler<Swing>

internal typealias Builder<T> = T.() -> T
