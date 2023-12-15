package com.syarif.mytodo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.syarif.mytodo.data.model.ItemToDo
import com.syarif.mytodo.data.model.ToDo
import com.syarif.mytodo.databinding.ItemTodoLayoutBinding
import com.syarif.mytodo.databinding.TodoLayoutBinding

class ItemToDoAdapter(
    private val tickItems: (ItemToDo) -> Unit
) : RecyclerView.Adapter<ItemToDoViewHolder>() {
    private val diffUtil = object : DiffUtil.ItemCallback<ItemToDo>(){
        override fun areItemsTheSame(oldItem: ItemToDo, newItem: ItemToDo): Boolean {
            return (oldItem.id == newItem.id)
        }

        override fun areContentsTheSame(oldItem: ItemToDo, newItem: ItemToDo): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemToDoViewHolder {
        val binding = ItemTodoLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemToDoViewHolder(binding, tickItems)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ItemToDoViewHolder, position: Int) {
        differ.currentList[position]?.let {
            holder.bind(it)
        }
    }

    fun setData(itemData : List<ItemToDo>){
        differ.submitList(itemData)
    }
}