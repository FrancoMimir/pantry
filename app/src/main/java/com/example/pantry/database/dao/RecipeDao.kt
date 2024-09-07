package com.example.pantry.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pantry.database.models.Recipe

@Dao
interface RecipeDao {

    // Insert a new recipe or replace an existing one if there is a conflict
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recipe: Recipe): Long

    // Update an existing recipe
    @Update
    fun update(recipe: Recipe)

    // Delete a recipe
    @Delete
    fun delete(recipe: Recipe)

    // Get a recipe by its ID
    @Query("SELECT * FROM Recipe WHERE id = :id")
    fun getRecipeById(id: Int): Recipe?

    // Get all recipes
    @Query("SELECT * FROM Recipe")
    fun getAllRecipes(): List<Recipe>

    // Get recipes by name (e.g., searching)
    @Query("SELECT * FROM Recipe WHERE name LIKE :name")
    fun findRecipesByName(name: String): List<Recipe>
}