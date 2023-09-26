package com.example.homeworktodolistwithroom

import android.app.Application
import androidx.room.Room

class MyApplication:Application() {
    lateinit var repo:EmployeeRepository

    override fun onCreate() {
        super.onCreate()

        instance = this
        val db = Room.databaseBuilder(context = this, klass = EmployeeDatabase::class.java,
            name = "employee_database").build()

        repo = EmployeeRepository(db)
    }

    companion object {
        private lateinit var instance: MyApplication

        fun getApp() = instance
    }
}
