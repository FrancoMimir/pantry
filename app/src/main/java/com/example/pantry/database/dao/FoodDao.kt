package com.example.pantry.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pantry.database.enums.StorageType
import com.example.pantry.database.models.Food
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    // Insert a new Food item or replace it if there is a conflict
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(food: Food): Long

    // Update an existing Food item
    @Update
    fun update(food: Food)

    // Delete a Food item
    @Delete
    fun delete(food: Food)

    // Get a Food item by its ID
    @Query("SELECT * FROM Food WHERE id = :id")
    fun getFoodById(id: Int): Food?

    // Get all Food items
    @Query("SELECT * FROM Food")
    fun getAllFoods(): Flow<List<Food>>

    // Find Food items by name
    @Query("SELECT * FROM Food WHERE name LIKE :name")
    fun findFoodsByName(name: String): Flow<List<Food>>

    // Find Food items by storage type
    @Query("SELECT * FROM Food WHERE storageType = :storageType")
    fun findFoodsByStorageType(storageType: StorageType): Flow<List<Food>>
}