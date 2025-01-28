package com.example.notesappcleanarch.add_note

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesappcleanarch.models.NoteModel
import com.example.notesappcleanarch.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddNoteViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _noteId: Int = -1
    private val repository = NotesRepository.getInstance()
    private var _title: MutableStateFlow<String> = MutableStateFlow("")
    var title = _title.asStateFlow()
    private var _description = MutableStateFlow<String>("")
    var description = _description.asStateFlow()

    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    init {

        val noteId = savedStateHandle.get<Int>("id") ?: -1
        _noteId = noteId


        if (_noteId != -1) {
            val note = repository.get(_noteId)
            if (note != null) {
                _title.value = note.title
                _description.value = note.description
            } else {

                _title.value = ""
                _description.value = ""
            }
        }
    }


    fun onTitleChange(title: String) {
        _title.value = title
    }


    fun onDescriptionChange(description: String) {
        _description.value = description
    }


    fun backIconOnClick(title: String, description: String) {
        _title.value = title
        _description.value = description


        val note = NoteModel(
            id = _noteId, title = _title.value, description = _description.value
        )


        viewModelScope.launch(Dispatchers.Main) {
            _event.emit(Event.NavigateBack(note))
        }
    }

    sealed class Event {
        data class NavigateBack(val note: NoteModel) : Event()
    }
}
