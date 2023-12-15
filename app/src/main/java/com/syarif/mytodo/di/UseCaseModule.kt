package com.syarif.mytodo.di

import com.syarif.mytodo.domain.repository.ToDoRepository
import com.syarif.mytodo.domain.usecase.GetToDoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetToDoUseCase(
        toDoRepository: ToDoRepository
    ): GetToDoUseCase{
        return GetToDoUseCase(toDoRepository)
    }
}