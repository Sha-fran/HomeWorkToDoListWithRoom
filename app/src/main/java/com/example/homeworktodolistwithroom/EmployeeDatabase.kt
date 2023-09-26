package com.example.homeworktodolistwithroom

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Employee::class], version = 1)
abstract class EmployeeDatabase:RoomDatabase() {
    abstract fun emloyeeDao():EmployeeDao
}
