package com.rms.remember.shared.ios

import androidx.room.Room
import androidx.room.RoomDatabase
import db.AppDataBase
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Named
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.createGraphFactory
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask
import presentation.di.TasksGraph
import kotlin.concurrent.Volatile

@DependencyGraph(AppScope::class)
interface IosAppGraph {

    val tasksGraphFactory: TasksGraph.Factory
    //val notesGraphFactory: NotesGraph.Factory

    @Provides
    fun providesDb(): AppDataBase = getDatabaseBuilder().build()

    @Provides
    @Named("ioDispatcher")
    fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @DependencyGraph.Factory
    fun interface Factory {
        fun create(): IosAppGraph
    }
}

object IosAppGraphHolder {

    private val LOCK = SynchronizedObject()


    @Volatile
    private var _appGraph: IosAppGraph? = null

    val appGraph: IosAppGraph
        get() = _appGraph
            ?: error("IosAppGraphHolder is not initialized. Call init() first.")

    /**
     * Must be called EXACTLY ONCE from iOS
     */
    fun setup() {
        if (_appGraph != null) {
            // Prevent double init (very important on iOS)
            return
        }

        synchronized(lock = LOCK) {
            if (_appGraph == null) {
                println("IosAppGraphHolder: Creating AppGraph")
                _appGraph = createIosAppGraph()
            }
        }
    }
}



@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}

fun getDatabaseBuilder(): RoomDatabase.Builder<AppDataBase> {
    val dbFilePath = documentDirectory() + "/my_room.db"
    return Room.databaseBuilder<AppDataBase>(
        name = dbFilePath,
    ).setDriver(androidx.sqlite.driver.bundled.BundledSQLiteDriver())
}

fun createIosAppGraph(): IosAppGraph = createGraphFactory<IosAppGraph.Factory>().create()