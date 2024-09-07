package com.example.pantry.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pantry.database.enums.StorageType

@Entity
data class Food (
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "storageType") val storageType : String,
    // These should all be per g
    @ColumnInfo(name = "calories") val calories: Int?,
    @ColumnInfo(name = "protein") val protein: Int?,
    @ColumnInfo(name = "carbs") val carbs: Int?,
    @ColumnInfo(name = "fiber") val fiber: Int?,
    @ColumnInfo(name = "fat") val fat: Int?,
){}