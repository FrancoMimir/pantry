package com.example.pantry

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pantry.database.DatabaseHelper
import com.example.pantry.database.enums.StorageType
import com.example.pantry.database.models.Food
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateFoodActivity : AppCompatActivity() {

    private lateinit var textViewList: List<TextView>
    private lateinit var storageTypeSpinner: Spinner
    private lateinit var saveButton: FloatingActionButton
    private lateinit var databaseHelper: DatabaseHelper
    val gson = Gson()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_food)
        textViewList = foodFields.map { id -> findViewById(id) }
        initSpinner()
        saveButton = findViewById(R.id.saveButton)
        saveButton.setOnClickListener{onSubmit()}
        databaseHelper = DatabaseHelper(this.applicationContext)
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

    @OptIn(DelicateCoroutinesApi::class)
    fun onSubmit(){
        val jsonObject = JsonObject()
        textViewList.forEach { textView: TextView ->
            run {
                val fieldId = resources.getResourceEntryName(textView.id)
                val fieldName = fieldId.substring(0, fieldId.lastIndexOf("Field"))
                jsonObject.addProperty(fieldName, textView.text.toString())
            }
        }
        jsonObject.addProperty("storageType", storageTypeSpinner.selectedItem.toString())
        val food = gson.fromJson(jsonObject, Food::class.java)
        GlobalScope.launch { databaseHelper.insertFood(food) }

    }

    val foodFields = listOf(
        R.id.nameField,
        R.id.massField,
        R.id.densityField,
        R.id.calorieField,
        R.id.proteinField,
        R.id.carbsField,
        R.id.fatsField
    )
}