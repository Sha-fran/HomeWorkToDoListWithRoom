package com.example.homeworktodolistwithroom

import java.util.concurrent.Executors

class EmployeeRepository (private val database: EmployeeDatabase) {
    private val executor = Executors.newSingleThreadExecutor()

    fun getAll() = database.emloyeeDao().getAll()

    fun add(employee: Employee) {
        executor.execute {
            database.emloyeeDao().add(employee)
        }
    }

    fun removeEmployee(employee: Employee) {
        executor.execute {
            database.emloyeeDao().delete(employee)
        }
    }
}
