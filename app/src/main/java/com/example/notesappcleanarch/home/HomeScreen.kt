package com.example.notesappcleanarch.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesappcleanarch.R
import com.example.notesappcleanarch.Routes
import com.example.notesappcleanarch.models.NoteModel
import com.google.gson.Gson
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    newNote: String? = null,
    modifier: Modifier,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navigateNext: (String) -> Unit = {}
) {

    val notes = viewModel.notesList
    LaunchedEffect(key1 = newNote) {
        if (newNote.isNullOrEmpty()) return@LaunchedEffect

        val newNoteObj = Gson().fromJson(newNote, NoteModel::class.java)
        viewModel.saveNote(newNoteObj)

    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is HomeViewModel.HomeEvent.NavigateNext -> navigateNext(event.route)
            }
        }
    }
    Scaffold(
        topBar = {
            Row(
                modifier
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.darkBlue))
                    .padding(start = 48.dp, top = 48.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.Start,
            ) {
                Text(
                    text = "Notes App",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.addNewNote()
                    navigateNext(Routes.AddNote + "/-1")
                },
                containerColor = colorResource(id = R.color.yellow),
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Note",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 16.dp)
                .background(color = colorResource(id = R.color.babyBlue)),
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(notes.size) { index ->
                val note = notes[index]
                NoteCard(modifier = modifier,
                    note = NoteModel(
                        id = note.id,
                        title = note.title,
                        description = note.description,
                    ),
                    onClick = {
                        viewModel.listItemOnClick(note.id)
                    }
                )
            }
        }
    }
}


