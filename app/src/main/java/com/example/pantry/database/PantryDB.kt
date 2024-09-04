package com.example.pantry.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pantry.database.dao.FoodDao
import com.example.pantry.database.dao.RecipeDao
import com.example.pantry.database.models.Food
import com.example.pantry.database.models.Recipe

@Database(entities = [Food::class, Recipe::class], version = 1, exportSchema = false)
abstract class PantryDB : RoomDatabase() {
    abstract fun foodDao(): FoodDao
    abstract fun recipeDao(): RecipeDao

    companion object instance{
        private var instance: PantryDB? = null
        fun get(context: Context): PantryDB? {
            if(instance== null){
                instance = Room.databaseBuilder(context, PantryDB::class.java, "db").build()
            }
            return instance
        }
    }
}