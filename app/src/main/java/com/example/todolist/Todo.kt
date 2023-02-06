package com.example.todolist


//This is just a constructor class in kotlin
data class Todo (
    val title : String,
    var isChecked : Boolean = false
)