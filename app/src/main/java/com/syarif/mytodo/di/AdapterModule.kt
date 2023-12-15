package com.syarif.mytodo.di

import com.syarif.mytodo.view.adapter.ItemToDoAdapter
import com.syarif.mytodo.view.adapter.ToDoAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

//    @Singleton
//    @Provides
//    fun provideToDoAdapter() : ToDoAdapter{
//        return ToDoAdapter()
//    }
//
//    @Singleton
//    @Provides
//    fun provideItemToDoAdapter() : ItemToDoAdapter {
//        return ItemToDoAdapter()
//    }
}