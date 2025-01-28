package com.example.notesappcleanarch.repository

import com.example.notesappcleanarch.models.NoteModel
import com.example.notesappcleanarch.models.dummyNotes

class NotesRepository private constructor(){
    companion object {
        private val _instance: NotesRepository? = null

        fun getInstance(): NotesRepository {
            return _instance ?: NotesRepository()
        }

    }

    val items = arrayListOf<NoteModel>().apply {
        addAll(dummyNotes())
    }

    fun getAll(): List<NoteModel> {
        return items
    }

    fun get(id: Int): NoteModel? {
        return items.firstOrNull { it.id == id }
    }

    fun insert(note: NoteModel): Int {
        val newId = items.size + 1
        val newNote = note.copy(
            id = newId
        )
        items.add(newNote)
        return newId
    }

    fun update(note: NoteModel) {
        val noteIndex = items.indexOfFirst { it.id == note.id }
        items[noteIndex] = note

    }
}