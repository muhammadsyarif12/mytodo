package com.syarif.mytodo.view

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.saadahmedsoft.popupdialog.PopupDialog
import com.saadahmedsoft.popupdialog.Styles
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener
import com.syarif.mytodo.R
import com.syarif.mytodo.data.model.ItemToDo
import com.syarif.mytodo.data.model.ToDo
import com.syarif.mytodo.databinding.ActivityMainBinding
import com.syarif.mytodo.util.Resource
import com.syarif.mytodo.view.adapter.ToDoAdapter
import com.syarif.mytodo.view.viewmodel.EditToDoViewModel
import com.syarif.mytodo.view.viewmodel.EditToDoViewModelFactory
import com.syarif.mytodo.view.viewmodel.ToDoViewModel
import com.syarif.mytodo.view.viewmodel.ToDoViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private val viewModel by lazy {
        ViewModelProvider(this, factory).get(ToDoViewModel::class.java)
    }

    private val editViewModel by lazy {
        ViewModelProvider(this, editFactory).get(EditToDoViewModel::class.java)
    }

    @Inject
    lateinit var factory: ToDoViewModelFactory

    @Inject
    lateinit var editFactory: EditToDoViewModelFactory

    lateinit var toDoAdapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toDoAdapter = ToDoAdapter(::deleteToDo, ::editToDo, ::tickToDo)

        binding.rvTodo.apply {
            adapter = toDoAdapter
            layoutManager = LinearLayoutManager(context)
        }

        setToDoData()
        listener()
    }

    private fun setToDoData(){
        viewModel.getAllToDoData()
        viewModel.todoData.observe(this){response ->
            when(response){
                is Resource.Success -> {
                    setProgressBar(false)
                    response.data.let {todoData ->
                        toDoAdapter.setData(todoData!!)
                        todoData.forEach {
                            viewModel.tempToDo.add(it)
                        }
//                        toDoAdapter.notifyDataSetChanged()
                    }
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

    private fun searchToDo(strSearch: String){
        viewModel.searchToDo(strSearch)
        viewModel.todoData.observe(this){response ->
            when(response){
                is Resource.Success -> {
                    setProgressBar(false)
                    response.data.let {todoData ->
                        toDoAdapter.setData(todoData!!)
//                        toDoAdapter.notifyDataSetChanged()
                    }
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

    private fun setProgressBar(isShown: Boolean){
        binding.pbLoading?.visibility  = if (isShown) View.VISIBLE else View.GONE
    }

    private fun listener(){
        binding.fabNew.setOnClickListener {
            var intent = Intent(this, NewToDo::class.java)
            startActivity(intent)
        }

        binding.edSearchTask.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                if(text.isNullOrBlank()){
                    setToDoData()
                }
                else if(!text.isNullOrBlank() && text.length > 3){
                    searchToDo("%$text%")
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    override fun onResume() {
        super.onResume()
        setToDoData()
    }

    private fun deleteToDo(todo: ToDo){
        popUpDialog(todo)
    }

    private fun editToDo(toDo: ToDo){
        var edit = Intent(this, EditToDo::class.java)
        edit.putExtra("todoData", toDo)
        startActivity(edit)
    }

    private fun tickToDo(toDo: ToDo, itemToDo: ItemToDo){
        var tempList = mutableListOf<ItemToDo>()
        toDo.todoList.forEach {
            if(it.id == itemToDo.id)
                tempList.add(itemToDo)
            else
                tempList.add(it)
        }
        toDo.todoList = tempList
//        println("-------> ${toDo.title} - ${itemToDo.description} - ${itemToDo.isChecked}")
//        viewModel.tempToDo.set(viewModel.tempToDo.indexOf(toDo), toDo)
        editViewModel.itemList = mutableListOf<ItemToDo>()
        toDo.todoList.forEach {
            editViewModel.itemList.add(it)
        }

        editViewModel.updateToDoData(toDo)

    }


    private fun popUpDialog(todoSelected: ToDo){
        PopupDialog.getInstance(this)
            .setStyle(Styles.STANDARD)
            .setHeading("Delete Task")
            .setPopupDialogIcon(R.drawable.baseline_delete_24)
            .setDescription("Are you sure to delete ${todoSelected.title}?")
            .setCancelable(true)
            .setPositiveButtonText("Delete")
            .setPositiveButtonBackground(R.drawable.rounded_bar_orange)
            .showDialog(object : OnDialogButtonClickListener(){
                override fun onPositiveClicked(dialog: Dialog?) {
                    super.onPositiveClicked(dialog)
                    viewModel.deleteToDo(todoSelected)
                    viewModel.deleteToDoData.observe(this@MainActivity){response ->
                        val todo: ToDo? = response.data
                        when(response){
                            is Resource.Success -> {
                                setProgressBar(false)
                                Toast.makeText(this@MainActivity, "Success to save ${todo!!.title}", Toast.LENGTH_SHORT).show()
                                viewModel.deleteToDoData.removeObservers(this@MainActivity)
                                setToDoData()
                            }
                            is Resource.Error -> {
                                setProgressBar(false)
                                response.message?.let{errorMessage ->
                                    Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_SHORT).show()
                                }
                            }
                            is Resource.Loading -> {
                                setProgressBar(true)
                            }
                        }
                    }
                }

                override fun onNegativeClicked(dialog: Dialog?) {
                    super.onNegativeClicked(dialog)
                }
            } )

    }
}