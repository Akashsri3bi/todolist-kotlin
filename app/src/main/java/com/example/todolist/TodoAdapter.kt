package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter
    (private val todos : MutableList<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            //Layout Inflater with its inflate function will use our xml here and convert
            // that to a kotlin class or a real view which we can use in our code

            LayoutInflater.from(parent.context).inflate(R.layout.item_todo,parent, false)

        )
    }

    //Two functions now for add todo items to the list and delete todo items from the list and display in recycler view

    private fun toggleStrikeThrough(tvTodoTitle : TextView , isChecked:Boolean){
        if(isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG

        }else{
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun addTodo(todo:Todo){
        todos.add(todo)
        //item inserted but we need to notify our recyclerView
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos(){
        todos.removeAll {
            todo-> todo.isChecked
        }

        notifyDataSetChanged() ;
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var curTodo = todos[position]
        holder.itemView.apply {
            findViewById<TextView>(R.id.tv_todo_title).text = curTodo.title
            findViewById<CheckBox>(R.id.cbDone).isChecked = curTodo.isChecked
            toggleStrikeThrough(findViewById<TextView>(R.id.tv_todo_title) , curTodo.isChecked)
            findViewById<CheckBox>(R.id.cbDone).setOnCheckedChangeListener { _, b ->  toggleStrikeThrough(findViewById<TextView>(R.id.tv_todo_title),b)}
            curTodo.isChecked = !curTodo.isChecked
        }

    }

    override fun getItemCount(): Int {
        return todos.size
    }
}