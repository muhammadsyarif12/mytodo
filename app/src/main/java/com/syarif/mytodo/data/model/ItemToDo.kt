package com.syarif.mytodo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.UUID

data class ItemToDo(
    @SerializedName("itemId")
    val id: String,
    @SerializedName("description")
    var description:String,
    @SerializedName("isChecked")
    var isChecked:Boolean
): Serializable
