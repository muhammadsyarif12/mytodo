package com.syarif.mytodo.data.repository

import android.util.Log
import com.syarif.mytodo.data.model.ToDo
import com.syarif.mytodo.data.repository.datasource.ToDoLocalDataSource
import com.syarif.mytodo.domain.repository.ToDoRepository
import com.syarif.mytodo.util.Resource
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(
    private val toDoLocalDataSource: ToDoLocalDataSource
) : ToDoRepository{
    override suspend fun getAllToDoFromRepository(): Resource<List<ToDo>> {
        lateinit var todoData: Resource<List<ToDo>>
        try {
            todoData = toDoLocalDataSource.getAllToDoFromLocal()
        }catch (e: Exception){
            Log.e(TAG, e.message.toString())
        }

        return todoData
    }

    override suspend fun addToDoToRepository(todo: ToDo): Resource<ToDo> {
        lateinit var todoData: Resource<ToDo>
        try {
            toDoLocalDataSource.saveToDoToLocal(todo)
            todoData = Resource.Success(todo)
        }catch (e: Exception){
            Log.e(TAG, e.message.toString())
        }

        return todoData
    }

    override suspend fun deleteToDoRepository(todo: ToDo): Resource<ToDo> {
        lateinit var todoData: Resource<ToDo>
        try {
            toDoLocalDataSource.deleteToDoFromLocal(todo)
            todoData = Resource.Success(todo)
        }catch (e: Exception){
            Log.e(TAG, e.message.toString())
        }

        return todoData
    }

    override suspend fun updateToDoRepository(todo: ToDo): Resource<ToDo> {
        lateinit var todoData: Resource<ToDo>
        try {
            toDoLocalDataSource.updateToDoFromLocal(todo)
            todoData = Resource.Success(todo)
        }catch (e: Exception){
            Log.e(TAG, e.message.toString())
        }

        return todoData
    }

    override suspend fun searchToDoRepository(strSearch: String): Resource<List<ToDo>> {
        lateinit var todoData: Resource<List<ToDo>>
        try {
            todoData = toDoLocalDataSource.searchToDoFromLocal(strSearch)
        }catch (e: Exception){
            Log.e(TAG, e.message.toString())
        }

        return todoData
    }


    companion object {
        private val TAG = ToDoRepositoryImpl::class.java.toString()
    }
}