package com.example.homeworktodolistwithroom

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val note:String
)
