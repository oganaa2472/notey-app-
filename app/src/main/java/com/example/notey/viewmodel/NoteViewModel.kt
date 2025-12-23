package com.example.notey.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notey.repository.NotesRepositories
import com.example.notey.roomdb.Note
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NotesRepositories) : ViewModel(){
    val allNotes: LiveData<List<Note>> = repository.allNotes
    // launch is a coroutine builder that launches a new
    // coroutine without blocking the current thread
    fun insert(note:Note){
        viewModelScope.launch {
            repository.insert(note)
        }
    }
}