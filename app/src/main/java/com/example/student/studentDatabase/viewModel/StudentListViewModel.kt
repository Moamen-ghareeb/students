package com.example.student.studentDatabase.viewModel

import androidx.lifecycle.*
import com.example.student.studentDatabase.model.Student
import com.example.student.studentDatabase.repository.Repo
import com.example.student.studentDatabase.repository.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StudentListViewModel @Inject constructor(private val repository:Repo) : ViewModel() {


    var readAllData= MutableLiveData<List<Student>>()

     fun readAll() {
         viewModelScope.launch {
             val all  = repository.allStudent()
             readAllData.postValue(all)
         }
    }

        fun insert(student: Student) =
            viewModelScope.launch {
                repository.insert(student)
            }

        suspend fun detail(studentId: Int): LiveData<Student> {
            return repository.studentDetail(studentId).asLiveData()
        }

        fun delete(student: Student) =
            viewModelScope.launch {
                repository.delete(student)
            }

        fun update(student: Student) =
            viewModelScope.launch{
                repository.update(student)
            }
    }

