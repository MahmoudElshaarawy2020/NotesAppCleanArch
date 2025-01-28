package com.example.notesappcleanarch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notesappcleanarch.add_note.AddNoteScreen
import com.example.notesappcleanarch.home.HomeScreen
import com.example.notesappcleanarch.home.HomeViewModel
import com.example.notesappcleanarch.ui.theme.NotesAppCleanArchTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesAppCleanArchTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.Home) {
                    composable(Routes.Home) {
                        val newNote = navController
                            .currentBackStackEntry
                            ?.savedStateHandle
                            ?.getStateFlow("new_note","")
                            ?.collectAsState()
                        HomeScreen(
                            newNote = newNote?.value,
                            modifier = Modifier,
                            viewModel = homeViewModel,
                            navigateNext = { navController.navigate(it) }
                        )
                    }

                    composable(
                        route = Routes.AddNote + "/{id}",
                        arguments = listOf(
                            navArgument("id") {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )) {
                        AddNoteScreen(
                            modifier = Modifier,
                            navigateBack = {newNote ->
                                val jsonStr = Gson().toJson(newNote)
                                navController
                                    .previousBackStackEntry
                                    ?.savedStateHandle
                                    ?.set("new_note", jsonStr)
                                navController.popBackStack()

                            }
                        )
                    }
                }
            }
        }
    }
}

