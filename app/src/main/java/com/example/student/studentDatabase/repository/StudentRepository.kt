package com.example.student.studentDatabase.repository


import com.example.student.studentDatabase.data.DatabaseProvider
import com.example.student.studentDatabase.data.StudentDao
import com.example.student.studentDatabase.model.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class StudentRepository @Inject constructor(private val databaseProvider: DatabaseProvider) : Repo {

    override suspend fun allStudent(): List<Student> = databaseProvider.getItems()

    override suspend fun studentDetail(id: Int): Flow<Student> = databaseProvider.getItem(id)

    override suspend fun delete(student: Student) = databaseProvider.delete(student)

    override suspend fun update(student: Student) = databaseProvider.update(student)

    override suspend fun insert(student: Student) = databaseProvider.insert(student)

}