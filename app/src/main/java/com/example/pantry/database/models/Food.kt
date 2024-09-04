package com.example.pantry.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pantry.database.enums.StorageType

@Entity
data class Food (
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "name") val name:String,
    @ColumnInfo(name = "storageType") val storageType : StorageType,
    @ColumnInfo(name = "mass") val mass : Int,
    @ColumnInfo(name = "brandName") val brandName: String?,
    @ColumnInfo(name = "calories") val calories: Int?,
    @ColumnInfo(name = "protein") val protein: Int?,
    @ColumnInfo(name = "carbs") val carbs: Int?,
    @ColumnInfo(name = "fiber") val fiber: Int?,
    @ColumnInfo(name = "fat") val fat: Int?,
){}