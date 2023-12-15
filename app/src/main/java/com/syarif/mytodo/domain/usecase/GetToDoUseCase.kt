package com.syarif.mytodo.domain.usecase

import android.icu.text.StringSearch
import com.syarif.mytodo.data.model.ToDo
import com.syarif.mytodo.domain.repository.ToDoRepository
import com.syarif.mytodo.util.Resource

class GetToDoUseCase (
    private val toDoRepository: ToDoRepository
){
    suspend fun execute() : Resource<List<ToDo>>{
        return toDoRepository.getAllToDoFromRepository()
    }

    suspend fun save(todo: ToDo) : Resource<ToDo>{
        return toDoRepository.addToDoToRepository(todo)
    }

    suspend fun delete(todo: ToDo): Resource<ToDo>{
        return toDoRepository.deleteToDoRepository(todo)
    }

    suspend fun update(todo: ToDo): Resource<ToDo>{
        return toDoRepository.updateToDoRepository(todo)
    }
    suspend fun search(stringSearch: String): Resource<List<ToDo>>{
        return toDoRepository.searchToDoRepository(stringSearch)
    }
}