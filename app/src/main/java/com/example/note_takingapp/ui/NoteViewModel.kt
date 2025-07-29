package com.example.note_takingapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.note_takingapp.data.Note
import com.example.note_takingapp.data.NoteDatabase
import com.example.note_takingapp.data.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = NoteRepository(NoteDatabase.get(app).noteDao())
    val notes = repo.allNotes

    fun add(title: String, body: String) = viewModelScope.launch {
        repo.add(Note(title = title, body = body))
    }

    fun delete(note: Note) = viewModelScope.launch {
        repo.delete(note)
    }
}

@Suppress("UNCHECKED_CAST")
class NoteVMFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        NoteViewModel(app) as T
}