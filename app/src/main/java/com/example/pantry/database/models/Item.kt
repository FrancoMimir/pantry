package com.example.pantry.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "foodId") val foodId : Long,
    @ColumnInfo(name = "mass") val mass : Int,
    @ColumnInfo(name = "servingSize") val servingSize : Int,
    @ColumnInfo(name = "brandName") val brandName: String?,
){
}