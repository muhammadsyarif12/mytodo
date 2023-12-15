package com.syarif.mytodo.di

import com.syarif.mytodo.data.database.ToDoDao
import com.syarif.mytodo.data.repository.datasource.ToDoLocalDataSource
import com.syarif.mytodo.data.repository.datasourceImpl.ToDoLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideToDoLocalDataSource(
        toDoDao: ToDoDao
    ): ToDoLocalDataSource{
        return ToDoLocalDataSourceImpl(toDoDao)
    }
}