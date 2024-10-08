package com.example.pantry.database

import android.content.Context
import com.example.pantry.database.models.Food
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class DatabaseHelper(context: Context) {
    private final val DATABASE_NAME = "db"
    private val sharedPreferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE)
    private val db = PantryDB.get(context.applicationContext)
    private val foodDao = db!!.foodDao()
    private val recipeDao = db!!.recipeDao()

    fun getAllFoods(): Job {
        return CoroutineScope(Dispatchers.IO).launch {
            foodDao.getAllFoods()
        }
    }

    fun insertFood(food : Food): Long {
        return foodDao.insert(food)
    }

    fun currentTime() :  String {
        val date: Date = Calendar.getInstance().time
        return SimpleDateFormat("yyyy-MM-dd '@' HH:mm:ss").format(date)
    }
}