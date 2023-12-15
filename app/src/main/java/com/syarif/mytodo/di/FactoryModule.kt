package com.syarif.mytodo.di

import android.app.Application
import com.syarif.mytodo.domain.usecase.GetToDoUseCase
import com.syarif.mytodo.view.viewmodel.AddToDoViewModelFactory
import com.syarif.mytodo.view.viewmodel.EditToDoViewModelFactory
import com.syarif.mytodo.view.viewmodel.ToDoViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideToDoViewModelFactory(
        app: Application,
        useCase: GetToDoUseCase
    ) : ToDoViewModelFactory{
        return ToDoViewModelFactory(app, useCase)
    }

    @Singleton
    @Provides
    fun provideAddToDoViewModelFactory(
        app: Application,
        useCase: GetToDoUseCase
    ) : AddToDoViewModelFactory {
        return AddToDoViewModelFactory(app, useCase)
    }

    @Singleton
    @Provides
    fun provideEditToDoViewModelFactory(
        app: Application,
        useCase: GetToDoUseCase
    ) : EditToDoViewModelFactory {
        return EditToDoViewModelFactory(app, useCase)
    }
}