package com.example.note_takingapp.data

class NoteRepository(private val dao: NoteDao) {
    val allNotes = dao.getAll()
    suspend fun add(note: Note) = dao.insert(note)
    suspend fun delete(note: Note) = dao.delete(note)
}