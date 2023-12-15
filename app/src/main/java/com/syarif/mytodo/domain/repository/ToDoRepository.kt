package com.syarif.mytodo.domain.repository

import com.syarif.mytodo.data.model.ToDo
import com.syarif.mytodo.util.Resource

interface ToDoRepository {
    suspend fun getAllToDoFromRepository(): Resource<List<ToDo>>
    suspend fun addToDoToRepository(todo: ToDo): Resource<ToDo>
    suspend fun deleteToDoRepository(todo: ToDo): Resource<ToDo>
    suspend fun updateToDoRepository(todo: ToDo): Resource<ToDo>
    suspend fun searchToDoRepository(strSearch: String): Resource<List<ToDo>>
}