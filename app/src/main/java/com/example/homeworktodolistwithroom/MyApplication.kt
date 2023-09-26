package com.example.homeworktodolistwithroom

import android.app.Application
import androidx.room.Room

class MyApplication:Application() {
    lateinit var repo:NoteRepository

    override fun onCreate() {
        super.onCreate()

        instance = this
        val db = Room.databaseBuilder(context = this, klass = NoteDatabase::class.java,
            name = "note_database").build()

        repo = NoteRepository(db)
    }

    companion object {
        private lateinit var instance: MyApplication

        fun getApp() = instance
    }
}
