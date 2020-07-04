package com.example.demo_workshop.screen.mainscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo_workshop.R
import com.example.demo_workshop.model.Todo

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var rvTodoList: RecyclerView
    private lateinit var btnAdd : Button
    private val list = ArrayList<Todo>()
    private val todoListAdapter = TodoListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvTodoList = findViewById(R.id.rv_todo_list)

        rvTodoList.setHasFixedSize(true)

        rvTodoList.layoutManager = LinearLayoutManager(this)

        list.add(Todo("Bekerja Bersama"))

        todoListAdapter.listTodo = list
        rvTodoList.adapter = todoListAdapter

        todoListAdapter.setOnItemClickCallback(object : TodoListAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Todo, position: Int) {
                list.removeAt(position)
                todoListAdapter.listTodo = list
            }
        })

        btnAdd = findViewById(R.id.btn_add)

        btnAdd.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btn_add -> {
                val etTodo = findViewById<TextView>(R.id.et_todo)
                list.add(Todo(etTodo.text.toString()))
                todoListAdapter.listTodo = list
            }
        }
    }
}