package com.example.todolist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())
        findViewById<RecyclerView>(R.id.rv_items).adapter = todoAdapter
        findViewById<RecyclerView>(R.id.rv_items).layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.addTodo).setOnClickListener {
            val todoTitle = findViewById<EditText>(R.id.et_title).text.toString()
            if(todoTitle.isNotEmpty()){
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                et_title.text.clear()
            }

        }

        dltTodo.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }

    }
}