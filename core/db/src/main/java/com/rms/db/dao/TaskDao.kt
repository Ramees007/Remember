package com.rms.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rms.db.model.TaskDbItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM TaskDbItem")
    fun getAll(): Flow<List<TaskDbItem>>

    @Query("SELECT * FROM TaskDbItem WHERE uid=:taskId")
    suspend fun getTask(taskId: Long): TaskDbItem?

    @Insert
    suspend fun insertAll(vararg tasks: TaskDbItem)

    @Query("UPDATE TaskDbItem SET task=:task, date=:date  WHERE uid=:id")
    suspend fun update(id: Long, task: String, date: String?)

    @Delete
    suspend fun delete(task: TaskDbItem)

    @Query("UPDATE TaskDbItem SET isDone=:isDone WHERE uid=:id")
    suspend fun update(id: Long, isDone: Boolean)
}