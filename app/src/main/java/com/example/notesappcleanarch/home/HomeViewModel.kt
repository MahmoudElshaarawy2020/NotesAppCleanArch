package com.example.notesappcleanarch.home

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesappcleanarch.Routes
import com.example.notesappcleanarch.models.NoteModel
import com.example.notesappcleanarch.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val notesList = mutableStateListOf<NoteModel>()

    private val repository = NotesRepository.getInstance()
    private val _eventFlow = MutableSharedFlow<HomeEvent>()
    val eventFlow = _eventFlow.asSharedFlow()
    val scope = viewModelScope

    init {
        val items = repository.getAll()
        notesList.addAll(items)
    }

    private val TAG = "HomeViewModel"

    fun listItemOnClick(id: Int) {
        scope.launch(Dispatchers.Main) {
            Log.d(TAG, "listItemOnClick: $id")
            val route = Routes.AddNote + "/$id"
            _eventFlow.emit(HomeEvent.NavigateNext(route))
        }
    }

    fun addNewNote() {
        Log.d(TAG, "addNewNote: ")
    }

    fun saveNote(newNoteObj: NoteModel) {
        Log.d(TAG, "saveNote: $newNoteObj")
        val isNoteEmpty = newNoteObj.let {
            it.title.isEmpty() && it.description.isEmpty()
        }
        if(isNoteEmpty) return
        if(newNoteObj.id == -1){
            val newId = repository.insert(newNoteObj)
            val newNote = newNoteObj.copy(
                id = newId
            )
            notesList.add(newNote)
        }else {
            repository.update(newNoteObj)
            val noteIndex = notesList.indexOfFirst { it.id == newNoteObj.id }
            notesList[noteIndex] = newNoteObj

        }

    }

    sealed class HomeEvent {
        data class NavigateNext(val route: String) : HomeEvent()
    }

}