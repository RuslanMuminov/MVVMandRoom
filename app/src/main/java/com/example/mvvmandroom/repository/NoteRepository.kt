package com.example.mvvmandroom.repository

import androidx.lifecycle.LiveData
import com.example.mvvmandroom.dao.NotesDao
import com.example.mvvmandroom.model.Note

class NoteRepository(private val notesDao: NotesDao) {

    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun insert(noteModel: Note){
        notesDao.insert(noteModel)
    }

    suspend fun delete(noteModel: Note){
        notesDao.delete(noteModel)
    }

    suspend fun update(noteModel: Note){
        notesDao.update(noteModel)
    }
}