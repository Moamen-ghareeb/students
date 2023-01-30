package com.example.student.studentDatabase.data


import com.example.student.studentDatabase.model.Student
import kotlinx.coroutines.flow.Flow

interface DatabaseProvider {
    suspend fun getItems(): List<Student>
    suspend fun getItem(id: Int): Flow<Student>
    suspend fun insert(item: Student)
    suspend fun insertList(item: List<Student>)
    suspend fun update(item: Student)
    suspend fun delete(item: Student)
    suspend fun deleteAll()
}