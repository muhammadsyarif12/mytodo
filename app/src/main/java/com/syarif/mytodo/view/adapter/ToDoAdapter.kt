package com.syarif.mytodo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.syarif.mytodo.data.model.ItemToDo
import com.syarif.mytodo.data.model.ToDo
import com.syarif.mytodo.databinding.TodoLayoutBinding

class ToDoAdapter(
        private val adapterPerform: (ToDo) -> Unit,
        private val adapterEdit: (ToDo) -> Unit,
    private val adapterTick:(ToDo, ItemToDo) -> Unit
    ) : RecyclerView.Adapter<ToDoViewHolder>() {
    private val diffUtil = object : DiffUtil.ItemCallback<ToDo>(){
        override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = TodoLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding, adapterPerform, adapterEdit, adapterTick, parent.context)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        differ.currentList[position]?.let {
            holder.bind(it)
        }
    }

    fun setData(newList: List<ToDo>){
        differ.submitList(newList)
    }

    fun getData(): List<ToDo>{
        return differ.currentList
    }
}