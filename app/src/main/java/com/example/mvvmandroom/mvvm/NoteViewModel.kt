package com.example.mvvmandroom.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmandroom.model.Note
import com.example.mvvmandroom.repository.NoteRepository
import com.example.mvvmandroom.room.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes: LiveData<List<Note>>
    private val repository: NoteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(noteModel: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(noteModel)
    }

    fun updateNote(noteModel: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.update(noteModel)
    }

    fun addNote(noteModel: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(noteModel)
    }
}