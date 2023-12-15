package com.syarif.mytodo.view.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.syarif.mytodo.data.model.ItemToDo
import com.syarif.mytodo.data.model.ToDo
import com.syarif.mytodo.domain.usecase.GetToDoUseCase
import com.syarif.mytodo.util.Resource
import com.syarif.mytodo.view.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val app: Application,
    private val getToDoUseCase: GetToDoUseCase
) : BaseViewModel(app){
    var todo = MutableLiveData<Resource<List<ToDo>>>()
    val todoData : LiveData<Resource<List<ToDo>>>
        get() = todo

    var tempToDo = mutableListOf<ToDo>()
    val tempToDo2 : List<ToDo>
        get() = tempToDo


    private var deleteToDo = MutableLiveData<Resource<ToDo>>()
    val deleteToDoData : LiveData<Resource<ToDo>>
        get() = deleteToDo

    fun getAllToDoData() = viewModelScope.launch(Dispatchers.IO){
        todo.postValue(Resource.Loading())
        try {
            val apiResult = getToDoUseCase.execute()
            todo.postValue(apiResult)
        }catch (e: Exception){
            todo.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun deleteToDo(toDo: ToDo) = viewModelScope.launch(Dispatchers.IO) {
        deleteToDo.postValue(Resource.Loading())
        try {
            deleteToDo.postValue(getToDoUseCase.delete(toDo))
        }catch (e: Exception){
            deleteToDo.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun searchToDo(strSearch: String) = viewModelScope.launch (Dispatchers.IO){
        todo.postValue(Resource.Loading())
        try {
            val apiResult = getToDoUseCase.search(strSearch)
            todo.postValue(apiResult)
        }catch (e: Exception){
            todo.postValue(Resource.Error(e.message.toString()))
        }
    }
}