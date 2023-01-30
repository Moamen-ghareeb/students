package com.example.student.studentDatabase.data

import androidx.room.*
import com.example.student.studentDatabase.model.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Query("SELECT * from Student ORDER BY name ASC")
    fun getItems(): List<Student>

    @Query("SELECT * from Student WHERE id = :id")
    fun getItem(id: Int): Flow<Student>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Student)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertList(item:List<Student>)

    @Update
    suspend fun update(item: Student)

    @Delete
    suspend fun delete(item: Student)

    @Query("DELETE FROM student")
    suspend fun deleteAll()
}