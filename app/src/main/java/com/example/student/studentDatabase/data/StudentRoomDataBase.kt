package com.example.student.studentDatabase.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.student.studentDatabase.model.Student
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Student::class], version = 1,exportSchema = false)
abstract class StudentRoomDataBase : RoomDatabase() {

    abstract fun studentDao(): StudentDao

    class Callback @Inject constructor(
        private val database : Provider<StudentRoomDataBase>,
    ) : RoomDatabase.Callback(){

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().studentDao()
           MainScope().launch {
                val st = Datasource.students
                dao.insertList(st)
                Log.d("callback","done")
            }
        }

    }

}
