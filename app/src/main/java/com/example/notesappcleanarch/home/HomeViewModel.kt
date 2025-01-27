package com.example.notesappcleanarch.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.notesappcleanarch.models.NoteModel
import com.example.notesappcleanarch.models.dummyNotes

class HomeViewModel: ViewModel() {
    private val _notes = ArrayList<NoteModel>(dummyNotes())
    val notes = _notes.toList()

    private val TAG = "HomeViewModel"
    fun listItemOnClick(id: Int){
        Log.d(TAG, "listItemOnClick: $id")
    }

    fun addNewNote(){
        Log.d(TAG, "addNewNote: ")
    }
}