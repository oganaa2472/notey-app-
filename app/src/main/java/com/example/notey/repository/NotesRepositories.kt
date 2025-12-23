package com.example.notey.repository

import androidx.lifecycle.LiveData
import com.example.notey.roomdb.Note
import com.example.notey.roomdb.NoteDao
import com.example.notey.roomdb.NotesDB


//1 fetching data from a network db,
// 2 loading data from the db
class NotesRepositories(private val noteDao: NoteDao) {
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()
    suspend fun insert(note:Note){
        return noteDao.insert(note);
    }
}