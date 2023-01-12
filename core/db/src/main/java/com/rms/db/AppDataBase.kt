package com.rms.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rms.db.dao.TaskDao
import com.rms.db.model.TaskDbItem

@Database(entities = [TaskDbItem::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object{
        const val NAME = "remember_db"
    }
}