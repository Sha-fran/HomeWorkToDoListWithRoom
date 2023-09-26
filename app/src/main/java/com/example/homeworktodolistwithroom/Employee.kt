package com.example.homeworktodolistwithroom

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee")
data class Employee(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val name:String,
    val position:String
)