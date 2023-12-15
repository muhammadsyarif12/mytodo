package com.syarif.mytodo.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.syarif.mytodo.data.model.ToDo

@Dao
interface ToDoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveToDo(todo: ToDo)

    @Query("SELECT * FROM to_do")
    fun getAllToDo(): List<ToDo>

    @Delete
    suspend fun deleteToDo(todo: ToDo)

    @Query("SELECT * FROM to_do where search LIKE :strSearch")
    suspend fun searchToDo(strSearch: String): List<ToDo>

    @Update
    suspend fun updateToDo(todo: ToDo)
}