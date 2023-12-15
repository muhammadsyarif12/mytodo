package com.syarif.mytodo.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.syarif.mytodo.data.model.ItemToDo
import com.syarif.mytodo.data.model.ToDo

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromToDoList(todoList: List<ToDo>): String{
        return gson.toJson(todoList)
    }

    @TypeConverter
    fun toToDoList(todoString: String): List<ToDo>{
        val listType = object : TypeToken<List<ToDo>>(){}.type
        return gson.fromJson(todoString, listType)
    }

    @TypeConverter
    fun fromToDo(todo: ToDo): String{
        return gson.toJson(todo)
    }

    @TypeConverter
    fun toToDo(todoString: String): ToDo{
        val type = object : TypeToken<ToDo>(){}.type
        return gson.fromJson(todoString, type)
    }

    @TypeConverter
    fun fromItemToDo(todo: ItemToDo): String{
        return gson.toJson(todo)
    }

    @TypeConverter
    fun toItemToDo(todoString: String): ItemToDo{
        val type = object : TypeToken<ItemToDo>(){}.type
        return gson.fromJson(todoString, type)
    }

    @TypeConverter
    fun fromItemToDoList(todoList: List<ItemToDo>): String{
        return gson.toJson(todoList)
    }

    @TypeConverter
    fun toItemToDoList(todoString: String): List<ItemToDo>{
        val listType = object : TypeToken<List<ItemToDo>>(){}.type
        return gson.fromJson(todoString, listType)
    }
}