package com.example.notesappcleanarch.models

data class NoteModel(
    val id: Int,
    val title: String,
    val description: String,
)

fun dummyNotes(): List<NoteModel> {
    val items = arrayListOf<NoteModel>()
    for (i in 1..3) {
        items.add(
            NoteModel(
                id = i,
                title = "Title $i",
                description = "Description $i"
            )
        )
    }
    return items
}
