package com.example.mvvmandroom.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "note_table")
 class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var noteTitle: String? = null,
    var noteDescription: String? = null
):Serializable