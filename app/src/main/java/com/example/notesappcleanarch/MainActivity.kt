package com.example.notesappcleanarch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesappcleanarch.home.HomeScreen
import com.example.notesappcleanarch.home.HomeViewModel
import com.example.notesappcleanarch.ui.theme.NotesAppCleanArchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesAppCleanArchTheme {
                HomeScreen(modifier = Modifier,viewModel = HomeViewModel())
            }
        }
    }
}

