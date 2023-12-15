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
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddToDoViewModel @Inject constructor(
    private val app: Application,
    private val getToDoUseCase: GetToDoUseCase
) : BaseViewModel(app){
    private var itemList = mutableListOf<ItemToDo>()

    val itemList2 : List<ItemToDo>
        get() = itemList

    private var newToDo = MutableLiveData<Resource<ToDo>>()
    val newToDoData : LiveData<Resource<ToDo>>
        get() = newToDo


    fun addItemToDo(strDescription: String){
        var item = ItemToDo(
            UUID.randomUUID().toString(),
            strDescription,
            false
        )

        itemList.add(item)
    }

    fun saveToDoData(title: String) = viewModelScope.launch(Dispatchers.IO) {
        //create todoObject instance
        var toDo = ToDo(
            null,
            title,
            itemList2,
            false,
            createSearchData()
            )

        newToDo.postValue(Resource.Loading())
        try {
            val result = getToDoUseCase.save(toDo)
            newToDo.postValue(result)
        }catch (e: Exception){
            newToDo.postValue(Resource.Error(e.message.toString()))
        }
    }

    fun createSearchData() : String{
        var result = ""
        itemList2.forEach {
            result += "${it.description} "
        }
        return result
    }

}