package com.syarif.mytodo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.syarif.mytodo.data.model.ToDo

@Database(
    entities = [ToDo::class],
    version = 3,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class ToDoDatabase :  RoomDatabase(){
    abstract fun toDoDao(): ToDoDao
}