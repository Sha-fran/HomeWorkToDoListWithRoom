package com.example.homeworktodolistwithroom

import java.util.concurrent.Executors

class NoteRepository (private val database: NoteDatabase) {
    private val executor = Executors.newSingleThreadExecutor()

    fun getAll() = database.noteDao().getAll()

    fun add(note: Note) {
        executor.execute {
            database.noteDao().add(note)
        }
    }

    fun removeEmployee(note: Note) {
        executor.execute {
            database.noteDao().delete(note)
        }
    }
}
