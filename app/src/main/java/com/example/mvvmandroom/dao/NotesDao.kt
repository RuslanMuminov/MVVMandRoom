package com.example.mvvmandroom.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvmandroom.model.Note

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(noteModel: Note)

    @Update
    suspend fun update(noteModel: Note)

    @Delete
    suspend fun delete(noteModel: Note)

    @Query("SELECT * from note_table order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>
}