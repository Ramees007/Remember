package db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import db.dao.NotesDao
import db.dao.TaskDao
import db.model.NotesDbItem
import db.model.TaskDbItem

@Database(entities = [TaskDbItem::class, NotesDbItem::class], version = 1, exportSchema = false)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    abstract fun notestDao(): NotesDao

    companion object {
        const val NAME = "remember_db"
    }
}

// The Room compiler generates the `actual` implementations.
@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDataBase> {
    override fun initialize(): AppDataBase
}