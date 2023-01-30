package com.example.student.studentDatabase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Student")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    @ColumnInfo(name = "Name")
    val name:String,
    @ColumnInfo(name="Grade")
    val grade:String,
    @ColumnInfo(name="Deatail")
    val detail:String
)