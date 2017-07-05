package com.codingblocks.restapiretrofitjson.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codingblocks.restapiretrofitjson.R
import com.codingblocks.restapiretrofitjson.models.Todo
import kotlinx.android.synthetic.main.activity_todo_list_item.view.*

/**
 * Created by amandhapola on 01/07/17.
 */
class TodoAdapter(var todoList:ArrayList<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TodoViewHolder {
        var v=LayoutInflater.from(parent?.context).inflate(R.layout.activity_todo_list_item,parent,false)
        return TodoViewHolder(v)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder?, position: Int) {
        var todo = todoList.get(position)
        if(holder!=null){
            if(todo.completed==true){
                holder.checkbox.isChecked=true
            }
            holder.tv_todo.setText(todo.title)
        }
    }

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var checkbox=itemView.chckbox
        var tv_todo=itemView.tv_todo
        var view =itemView
    }
}