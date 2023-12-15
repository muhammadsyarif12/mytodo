package com.syarif.mytodo.di

import android.app.Application
import androidx.room.Room
import com.syarif.mytodo.data.database.ToDoDao
import com.syarif.mytodo.data.database.ToDoDatabase
import com.syarif.mytodo.util.AppConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideToDoDatabase(app: Application): ToDoDatabase{
        return Room.databaseBuilder(app, ToDoDatabase::class.java, AppConstant.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideToDoDao(toDoDatabase: ToDoDatabase): ToDoDao{
        return toDoDatabase.toDoDao()
    }
}