package com.syarif.mytodo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.syarif.mytodo.R
import com.syarif.mytodo.data.model.ItemToDo
import com.syarif.mytodo.data.model.ToDo
import com.syarif.mytodo.databinding.ActivityNewToDoBinding
import com.syarif.mytodo.util.Resource
import com.syarif.mytodo.view.adapter.ItemToDoAdapter
import com.syarif.mytodo.view.viewmodel.AddToDoViewModel
import com.syarif.mytodo.view.viewmodel.AddToDoViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewToDo : AppCompatActivity() {
    lateinit var binding: ActivityNewToDoBinding

    private val  viewModel by lazy{
        ViewModelProvider(this, factory).get(AddToDoViewModel::class.java)
    }

    @Inject
    lateinit var factory: AddToDoViewModelFactory
    lateinit var itemAdapter: ItemToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewToDoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        itemAdapter = ItemToDoAdapter(::tickItems)

        binding.rvInputItem.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(context)
        }

        listener()
    }

    fun listener(){
        binding.btnAdd.setOnClickListener {
            if(!binding.edDescription.text.isNullOrBlank()){
                viewModel.addItemToDo(binding.edDescription.text.toString())
                itemAdapter.setData(viewModel.itemList2)
                itemAdapter.notifyDataSetChanged()
                clearDisplay()
            }
        }

        binding.btnDone.setOnClickListener {
            if(isValidData()){
                viewModel.saveToDoData(binding.edTitle.text.toString())
                viewModel.newToDoData.observe(this){response ->
                    val todo: ToDo? = response.data
                    when(response){
                        is Resource.Success -> {
                            setProgressBar(false)
                            Toast.makeText(this, "Success to save ${todo!!.title}", Toast.LENGTH_SHORT).show()
                            viewModel.newToDoData.removeObservers(this)
                            finish()
                        }
                        is Resource.Error -> {
                            setProgressBar(false)
                            response.message?.let{errorMessage ->
                                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                            }
                        }
                        is Resource.Loading -> {
                            setProgressBar(true)
                        }
                    }

                }
            }
        }
    }

    fun clearDisplay(){
        binding.edDescription.text = null
    }

    fun isValidData(): Boolean{
        if(binding.edTitle.text.isNullOrBlank()){
            Toast.makeText(applicationContext, "Title cannot be blank", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        if(viewModel.itemList2.isEmpty()){
            Toast.makeText(applicationContext, "Task cannot be blank", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        return true
    }

    private fun setProgressBar(isShown: Boolean){
        binding.pbSave?.visibility  = if (isShown) View.VISIBLE else View.GONE
    }

    private fun tickItems(itemToDo: ItemToDo){

    }
}