package com.syarif.mytodo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.syarif.mytodo.data.model.ItemToDo
import com.syarif.mytodo.data.model.ToDo
import com.syarif.mytodo.databinding.ItemEditTodoLayoutBinding
import com.syarif.mytodo.databinding.ItemTodoLayoutBinding
import com.syarif.mytodo.databinding.TodoLayoutBinding

class ItemEditToDoAdapter(
    private val performDelete:(ItemToDo) -> Unit
) : RecyclerView.Adapter<ItemEditToDoViewHolder>() {
    private val diffUtil = object : DiffUtil.ItemCallback<ItemToDo>(){
        override fun areItemsTheSame(oldItem: ItemToDo, newItem: ItemToDo): Boolean {
            return (oldItem.id == newItem.id)
        }

        override fun areContentsTheSame(oldItem: ItemToDo, newItem: ItemToDo): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemEditToDoViewHolder {
        val binding = ItemEditTodoLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemEditToDoViewHolder(binding, performDelete)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ItemEditToDoViewHolder, position: Int) {
        differ.currentList[position]?.let {
            holder.bind(it)
        }
    }

    fun setData(itemData : List<ItemToDo>){
        differ.submitList(itemData)
    }

    fun getData(): List<ItemToDo>{
        return differ.currentList
    }
}