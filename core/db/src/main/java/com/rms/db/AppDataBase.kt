package com.rms.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rms.db.dao.NotesDao
import com.rms.db.dao.TaskDao
import com.rms.db.model.NotesDbItem
import com.rms.db.model.TaskDbItem

@Database(entities = [TaskDbItem::class, NotesDbItem::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    abstract fun notestDao(): NotesDao

    companion object {
        const val NAME = "remember_db"
    }
}