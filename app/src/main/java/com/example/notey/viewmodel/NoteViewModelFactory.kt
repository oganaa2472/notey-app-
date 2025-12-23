package com.example.notey.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notey.repository.NotesRepositories

class NoteViewModelFactory(private val repository: NotesRepositories)
    : ViewModelProvider.Factory{
    // if your viewmodel requires additional parameters
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NoteViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")

    }
}