package com.example.notesappcleanarch.home

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.notesappcleanarch.models.NoteModel
import com.example.notesappcleanarch.models.dummyNotes

class HomeViewModel: ViewModel() {
    val notes = mutableStateListOf<NoteModel>().apply {
        addAll(dummyNotes())
    }

    private val TAG = "HomeViewModel"
    fun listItemOnClick(id: Int){
        Log.d(TAG, "listItemOnClick: $id")
    }

    fun addNewNote(){
        Log.d(TAG, "addNewNote: ")
    }

    fun saveNote(newNoteObj: NoteModel) {
        Log.d(TAG, "saveNote: $newNoteObj")
        notes.add(newNoteObj)
    }
}