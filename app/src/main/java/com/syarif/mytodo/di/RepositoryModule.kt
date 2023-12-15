package com.syarif.mytodo.di

import com.syarif.mytodo.data.repository.ToDoRepositoryImpl
import com.syarif.mytodo.data.repository.datasource.ToDoLocalDataSource
import com.syarif.mytodo.domain.repository.ToDoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideToDoRepository(
        toDoLocalDataSource: ToDoLocalDataSource
    ): ToDoRepository{
        return ToDoRepositoryImpl(toDoLocalDataSource)
    }
}