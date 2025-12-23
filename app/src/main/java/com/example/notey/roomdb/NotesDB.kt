package com.example.notey.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
// access it only one thread
@Database(entities = [Note::class], version = 1)
abstract class NotesDB : RoomDatabase(){
    abstract val notesDao : NoteDao

    companion object{
        @Volatile // single instances
        private var INSTANCE : NotesDB? = null
        fun getInstance(context: Context): NotesDB{
            synchronized(lock = this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context = context.applicationContext,
                        NotesDB::class.java,
                        name="notes_db"
                    ).build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }
    // creating the database object
}