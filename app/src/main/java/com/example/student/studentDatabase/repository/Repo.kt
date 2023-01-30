package com.example.student.studentDatabase.repository

import com.example.student.studentDatabase.model.Student
import kotlinx.coroutines.flow.Flow

interface Repo {

    suspend fun  allStudent(): List<Student>
    suspend fun studentDetail(id:Int): Flow<Student>

    suspend fun delete (student: Student)

    suspend fun update(student: Student)

    suspend fun insert(student: Student)


}