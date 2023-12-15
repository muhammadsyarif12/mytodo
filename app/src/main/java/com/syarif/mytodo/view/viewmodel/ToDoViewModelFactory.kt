package com.syarif.mytodo.view.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.syarif.mytodo.domain.usecase.GetToDoUseCase

class ToDoViewModelFactory(
    private val application: Application,
    private val getToDoUseCase: GetToDoUseCase
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ToDoViewModel(
            application, getToDoUseCase
        ) as T
    }
}