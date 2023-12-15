package com.syarif.mytodo.data.repository.datasource

import com.syarif.mytodo.data.model.ToDo
import com.syarif.mytodo.util.Resource

interface ToDoLocalDataSource {
    suspend fun saveToDoToLocal(toDo: ToDo)
    suspend fun getAllToDoFromLocal(): Resource<List<ToDo>>
    suspend fun deleteToDoFromLocal(toDo: ToDo)
    suspend fun updateToDoFromLocal(toDo: ToDo)
    suspend fun searchToDoFromLocal(strSearch: String): Resource<List<ToDo>>
}