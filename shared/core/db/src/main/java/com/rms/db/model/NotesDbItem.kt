package com.rms.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NotesDbItem(
    @PrimaryKey(autoGenerate = true) val uid: Long = 0,
    val note: String
)
