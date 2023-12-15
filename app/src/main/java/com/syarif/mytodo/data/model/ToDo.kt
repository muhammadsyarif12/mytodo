package com.syarif.mytodo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    tableName = "to_do"
)

data class ToDo(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id:Int?,
    @SerializedName("title")
    var title:String,
    @SerializedName("toDoList")
    var todoList: List<ItemToDo>,
    @SerializedName("isComplete")
    var isComplete: Boolean,
    @SerializedName("search")
    var search:String
): Serializable
