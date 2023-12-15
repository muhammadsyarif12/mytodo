package com.syarif.mytodo.view.adapter

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syarif.mytodo.data.model.ItemToDo
import com.syarif.mytodo.data.model.ToDo
import com.syarif.mytodo.databinding.ActivityMainBinding
import com.syarif.mytodo.databinding.TodoLayoutBinding
import com.syarif.mytodo.view.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import java.security.PrivateKey

class ToDoViewHolder(
    private val binding: TodoLayoutBinding,
    private val adapterPerform: (ToDo) -> Unit,
    private val adapterEdit: (ToDo) -> Unit,
    private val tickItem: (ToDo, ItemToDo) -> Unit,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root){
    lateinit var itemAdapter: ItemToDoAdapter
    lateinit var selectedToDo: ToDo

    fun bind(todo: ToDo){
        selectedToDo = todo
        binding.txtTitle.text = todo.title

        itemAdapter = ItemToDoAdapter(::tickItems)
        itemAdapter.setData(todo.todoList)

        binding.rvItem.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(context)
        }

        binding.btnDelete.setOnClickListener {
            adapterPerform(todo)
        }

        binding.cardTodo.setOnClickListener {
            adapterEdit(todo)
        }
    }

    fun tickItems(itemToDo: ItemToDo){
        tickItem(selectedToDo, itemToDo)
    }
}