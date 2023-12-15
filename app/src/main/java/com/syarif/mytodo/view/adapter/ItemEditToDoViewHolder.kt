package com.syarif.mytodo.view.adapter

import android.graphics.Color
import android.graphics.Paint
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syarif.mytodo.data.model.ItemToDo
import com.syarif.mytodo.data.model.ToDo
import com.syarif.mytodo.databinding.ActivityMainBinding
import com.syarif.mytodo.databinding.ItemEditTodoLayoutBinding
import com.syarif.mytodo.databinding.ItemTodoLayoutBinding
import com.syarif.mytodo.databinding.TodoLayoutBinding
import com.syarif.mytodo.view.MainActivity

class ItemEditToDoViewHolder(
    private val binding: ItemEditTodoLayoutBinding,
    private val performDelete: (ItemToDo) -> Unit
) : RecyclerView.ViewHolder(binding.root){
    fun bind(itemTodo: ItemToDo){
        binding.txtDescription.setText(itemTodo.description)
        if(itemTodo.isChecked)
            binding.txtDescription.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        else
            binding.txtDescription.paintFlags = Paint.LINEAR_TEXT_FLAG

        binding.btnDeleteItem.setOnClickListener {
            performDelete(itemTodo)
        }
    }

}