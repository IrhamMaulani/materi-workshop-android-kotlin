package com.example.demo_workshop.screen.mainscreen

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo_workshop.R
import com.example.demo_workshop.model.Todo
import kotlinx.android.synthetic.main.todo_list_item.view.*
import com.example.demo_workshop.screen.mainscreen.TodoListAdapter.OnItemClickCallback as onItemClickCallback1

class TodoListAdapter() :
    RecyclerView.Adapter<TodoListAdapter.ListViewHolder>() {

    private var onItemClickCallback: onItemClickCallback1? = null


    var listTodo = listOf<Todo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.todo_list_item, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listTodo.size

    override fun onBindViewHolder(holder: TodoListAdapter.ListViewHolder, position: Int) {
        holder.bind(listTodo[position])
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var todoText: TextView = itemView.findViewById(R.id.tv_todo)
        fun bind(todo: Todo) {
            todoText.text = todo.name
            itemView.ib_delete.setOnClickListener { onItemClickCallback?.onItemClicked(todo, adapterPosition) }
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Todo, position: Int)
    }

}
