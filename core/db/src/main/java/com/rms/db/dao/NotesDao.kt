package com.rms.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rms.db.model.NotesDbItem
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM NotesDbItem")
    fun getNotes(): Flow<List<NotesDbItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg tasks: NotesDbItem)

    @Query("SELECT * from NotesDbItem WHERE uid=:id LIMIT 1")
    suspend fun getNote(id:Long): NotesDbItem?
}