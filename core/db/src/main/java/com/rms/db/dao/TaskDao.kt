package com.rms.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM TaskDbItem")
    fun getAll(): Flow<List<com.rms.db.model.TaskDbItem>>

    @Insert
    suspend fun insertAll(vararg tasks: com.rms.db.model.TaskDbItem)

    @Delete
    suspend fun delete(task: com.rms.db.model.TaskDbItem)

    @Query("UPDATE TaskDbItem SET isDone=:isDone WHERE uid=:id")
    suspend fun update(id: Long, isDone: Boolean)
}