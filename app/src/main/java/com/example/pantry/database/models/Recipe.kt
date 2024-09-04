package com.example.pantry.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "instructions") val instructions:String,
    @ColumnInfo(name = "ingredients") val ingredients: Map<String, Int>,
    @ColumnInfo(name = "pictures") val pictures : String
)