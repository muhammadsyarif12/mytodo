package com.syarif.mytodo.data.repository.datasourceImpl

import com.syarif.mytodo.data.database.ToDoDao
import com.syarif.mytodo.data.model.ToDo
import com.syarif.mytodo.data.repository.datasource.ToDoLocalDataSource
import com.syarif.mytodo.util.Resource
import javax.inject.Inject

class ToDoLocalDataSourceImpl @Inject constructor(
    private val toDoDao: ToDoDao
) : ToDoLocalDataSource{
    override suspend fun saveToDoToLocal(toDo: ToDo){
        return toDoDao.saveToDo(toDo)
    }

    override suspend fun getAllToDoFromLocal(): Resource<List<ToDo>> {
        return Resource.Success(toDoDao.getAllToDo())
    }

    override suspend fun deleteToDoFromLocal(toDo: ToDo) {
        return toDoDao.deleteToDo(toDo)
    }

    override suspend fun updateToDoFromLocal(toDo: ToDo) {
        return toDoDao.updateToDo(toDo)
    }

    override suspend fun searchToDoFromLocal(strSearch: String): Resource<List<ToDo>> {
        return Resource.Success(toDoDao.searchToDo(strSearch))
    }
}