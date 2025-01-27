package com.example.notesappcleanarch.add_note

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesappcleanarch.R
import com.example.notesappcleanarch.models.NoteModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddNoteScreen(
    modifier: Modifier = Modifier,
    viewModel: AddNoteViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navigateBack: (NoteModel) -> Unit = {}
) {
    val title = viewModel.title.collectAsState()
    val description = viewModel.description.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.event.collectLatest { event ->
            when (event) {
                is AddNoteViewModel.Event.NavigateBack -> navigateBack(event.note)
            }
        }
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.darkBlue))
            .padding(top = 32.dp)) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.darkBlue))
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = colorResource(id = R.color.white),
                modifier = modifier
                    .size(35.dp)
                    .clickable { viewModel.backIconOnClick(title.value, description.value) }
            )
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = colorResource(id = R.color.white),
                modifier = modifier
                    .size(35.dp)
                    .clickable { }
            )

        }

        TextField(
            value = title.value,
            onValueChange = {viewModel.onTitleChange(it)},
            modifier = modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = {
                Text(
                    text = "Enter title",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold)
            },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        )

        TextField(
            value = description.value,
            onValueChange = {viewModel.onDescriptionChange(it)},
            modifier = modifier.fillMaxSize(),
            placeholder = {
                Text(
                    text = "Description",
                    fontSize = 24.sp)
            },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 24.sp,

            )
        )
    }
}
