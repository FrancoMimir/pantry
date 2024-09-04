package com.example.pantry

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.pantry.database.DatabaseHelper
import com.example.pantry.database.enums.StorageType
import com.example.pantry.database.models.Food
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.JsonObject

class CreateFoodActivity : AppCompatActivity() {

    private lateinit var textViewList: List<TextView>
    private lateinit var storageTypeSpinner: Spinner
    private lateinit var saveButton: FloatingActionButton
    private val databaseHelper: DatabaseHelper = DatabaseHelper(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_food)
        textViewList = foodFields.map { id -> findViewById(id) }
        initSpinner()
        saveButton = findViewById(R.id.saveButton)
        saveButton.setOnClickListener{onSubmit()}
    }

    fun initSpinner(){
        storageTypeSpinner = findViewById(R.id.storageTypeField)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            StorageType.entries.map { it.toString() }
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        storageTypeSpinner.adapter = adapter
    }

    fun onSubmit(){
        val jsonObject = JsonObject()
        textViewList.forEach { textView: TextView ->
            run {
                val fieldId = resources.getResourceEntryName(textView.id)
                val fieldName = fieldId.substring(0, fieldId.lastIndexOf("Field"))
                jsonObject.addProperty(fieldName, textView.text.toString())
            }
        }
        val food = Gson().fromJson(jsonObject, Food::class.java)
        databaseHelper.insertFood(food)
    }

    val foodFields = listOf(
        R.id.nameField,
        R.id.massField,
        R.id.densityField,
        R.id.storageTypeField,
        R.id.calorieField,
        R.id.proteinField,
        R.id.carbsField,
        R.id.fatsField
    )
}