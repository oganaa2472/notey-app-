package com.example.notey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Modifier.Companion
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModelProvider
import com.example.notey.repository.NotesRepositories
import com.example.notey.roomdb.Note
import com.example.notey.roomdb.NotesDB
import com.example.notey.screens.DisplayDialog
import com.example.notey.screens.DisplayNotesList
import com.example.notey.ui.theme.NoteyTheme
import com.example.notey.viewmodel.NoteViewModel
import com.example.notey.viewmodel.NoteViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Room DB
            val database = NotesDB.getInstance(applicationContext)

            val repository = NotesRepositories(database.notesDao)

            val viewModelFactory = NoteViewModelFactory(repository)

            val noteViewModel =
                ViewModelProvider(
                    this,viewModelFactory)[NoteViewModel::class.java]

            NoteyTheme {
                Scaffold(
                    floatingActionButton = {MyFAB(noteViewModel)}
                ) {
                    // observing livedata from a view model
                    // getting its state in a composable function
                    val notes by noteViewModel
                        .allNotes.observeAsState(emptyList())
                    // .observeAsState converts  a live data into
                    // a state object that can be observed
                    // with in a composable fun
                    DisplayNotesList(notes = notes)
                }

            }
        }
    }
}

@Composable
fun MyFAB(viewModel: NoteViewModel){
    var showDialog by remember { mutableStateOf(false) }
    DisplayDialog(viewModel = viewModel,showDialog = showDialog, onDismiss = {
        showDialog  = false
    }

    )
    FloatingActionButton(
        onClick = {
            showDialog = true
        },
        containerColor = Color.Blue,
        contentColor = Color.White
    ) {

        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add Note")
    }
}