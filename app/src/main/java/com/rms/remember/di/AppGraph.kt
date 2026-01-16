package com.rms.remember.di

import android.content.Context
import com.rms.db.AppDataBase
import com.rms.remember.shared.feature.notes.presentation.di.NotesGraph
import com.rms.remember.shared.feature.tasks.presentation.di.TasksGraph
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Named
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.createGraphFactory
import getDatabaseBuilder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@DependencyGraph(AppScope::class)
interface AppGraph {

    val tasksGraphFactory: TasksGraph.Factory

    val notesGraphFactory: NotesGraph.Factory


    @Provides
    fun providesDb(context: Context): AppDataBase = getDatabaseBuilder(context).build()

    @Provides
    @Named("ioDispatcher")
    fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO


    @DependencyGraph.Factory
    fun interface Factory {
        fun create(@Provides context: Context): AppGraph
    }
}

fun createAppGraph(context: Context) = createGraphFactory<AppGraph.Factory>().create(context)