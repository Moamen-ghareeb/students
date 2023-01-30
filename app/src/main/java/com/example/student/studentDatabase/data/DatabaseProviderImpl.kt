package com.example.student.studentDatabase.data

import com.example.student.studentDatabase.model.Student
import com.example.student.studentDatabase.repository.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseProviderImpl @Inject constructor(private val  studentRoomDataBase: StudentRoomDataBase):DatabaseProvider {
    override suspend fun getItems(): List<Student> {
       return studentRoomDataBase.studentDao().getItems()
    }

    override suspend fun getItem(id: Int): Flow<Student> {
       return studentRoomDataBase.studentDao().getItem(id)
    }

    override suspend fun insert(item: Student) {
        return studentRoomDataBase.studentDao().insert(item)
    }

    override suspend fun insertList(item: List<Student>) {
      return studentRoomDataBase.studentDao().insertList(item)
    }

    override suspend fun update(item: Student) {
        return studentRoomDataBase.studentDao().update(item)
    }

    override suspend fun delete(item: Student) {
        return studentRoomDataBase.studentDao().delete(item)
    }

    override suspend fun deleteAll() {
       return studentRoomDataBase.studentDao().deleteAll()
    }

}