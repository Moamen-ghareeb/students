package com.example.student.studentDatabase.module

import android.content.Context
import androidx.room.Room
import com.example.student.studentDatabase.data.DatabaseProvider
import com.example.student.studentDatabase.data.DatabaseProviderImpl
import com.example.student.studentDatabase.data.StudentDao
import com.example.student.studentDatabase.data.StudentRoomDataBase
import com.example.student.studentDatabase.repository.Repo
import com.example.student.studentDatabase.repository.StudentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object StudentModule {

    @Singleton
    @Provides
    fun getDatabase(
        @ApplicationContext context: Context,
        callback : StudentRoomDataBase.Callback
    ): StudentRoomDataBase {
        return Room
            .databaseBuilder(
            context.applicationContext,
            StudentRoomDataBase::class.java,
            "student"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .addCallback (callback)
            .build()
    }

    @Provides
    fun studentDao(database: StudentRoomDataBase): StudentDao {
        return database.studentDao()
    }
    @Singleton
    @Provides
    fun database(studentRoomDataBase: StudentRoomDataBase):DatabaseProvider{
        return DatabaseProviderImpl(studentRoomDataBase)
    }

    @Singleton
    @Provides
    fun repo(databaseProvider: DatabaseProvider):Repo{
        return StudentRepository(databaseProvider)
    }

}
