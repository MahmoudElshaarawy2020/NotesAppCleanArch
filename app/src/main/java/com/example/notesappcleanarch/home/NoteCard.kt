package com.example.notesappcleanarch.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.notesappcleanarch.R
import com.example.notesappcleanarch.models.NoteModel

@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    note: NoteModel,
    onClick: (Int) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onClick(note.id) }
    ) {
        Column(
            modifier = modifier.fillMaxWidth()
            .background(color = colorResource(id = R.color.white))
        ) {
            Text(
                text = note.title,
                color = Color.Black,
                modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                fontWeight = FontWeight.Bold
            )

            Text(
                text = note.description,
                color = Color.Black,
                modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}
