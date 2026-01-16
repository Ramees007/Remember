package com.rms.remember.di

import com.rms.remember.shared.feature.tasks.presentation.di.TasksGraph
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.createGraphFactory

//@DependencyGraph(AppScope::class)
//interface AppGraph {
//
//    val tasksGraph: TasksGraph
//
//    @DependencyGraph.Factory
//    fun interface Factory {
//        fun create(): AppGraph
//    }
//}
//
//fun createAppGraph() = createGraphFactory<AppGraph.Factory>().create()