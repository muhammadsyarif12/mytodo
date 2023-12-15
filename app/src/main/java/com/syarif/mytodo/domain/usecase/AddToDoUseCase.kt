package com.syarif.mytodo.domain.usecase

import com.syarif.mytodo.data.model.ToDo
import com.syarif.mytodo.domain.repository.ToDoRepository
import com.syarif.mytodo.util.Resource

class AddToDoUseCase (
    private val toDoRepository: ToDoRepository
){
    suspend fun execute() : Resource<List<ToDo>>{
        return toDoRepository.getAllToDoFromRepository()
    }
}