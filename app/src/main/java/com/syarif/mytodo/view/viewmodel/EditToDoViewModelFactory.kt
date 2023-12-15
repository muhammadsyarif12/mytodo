package com.syarif.mytodo.view.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.syarif.mytodo.domain.usecase.GetToDoUseCase

class EditToDoViewModelFactory(
    private val application: Application,
    private val getToDoUseCase: GetToDoUseCase
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EditToDoViewModel(
            application,
            getToDoUseCase
        ) as T
    }
}