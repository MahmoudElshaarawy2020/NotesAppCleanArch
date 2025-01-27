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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesappcleanarch.R
import com.example.notesappcleanarch.Routes
import com.example.notesappcleanarch.models.NoteModel

@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: HomeViewModel,
    navigateToAddNote: (String) -> Unit = {}
) {

    val notes = viewModel.notes

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
                onClick = { viewModel.addNewNote()
                          navigateToAddNote(Routes.AddNote)},
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


