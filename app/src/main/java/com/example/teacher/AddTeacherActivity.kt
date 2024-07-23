package com.example.teacher

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts


class AddTeacherActivity : ComponentActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextAge: EditText
    private lateinit var editTextDept: EditText
    private lateinit var spinnerStatus: Spinner

    private lateinit var btnSave: Button
    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_teacher)

        editTextName = findViewById(R.id.editTextName)
        editTextAge = findViewById(R.id.editTextAge)
        editTextDept = findViewById(R.id.editTextDept)

        spinnerStatus = findViewById(R.id.spinnerStatus)
        btnSave = findViewById(R.id.btnSave)

        ArrayAdapter.createFromResource(
            this,
            R.array.status_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerStatus.adapter = adapter
        }

        // Get the data if we are editing an existing teacher
        intent?.let {
            editTextName.setText(it.getStringExtra("name"))
            editTextAge.setText(it.getStringExtra("age"))
            editTextDept.setText(it.getStringExtra("department"))
            val status = it.getStringExtra("department")
            position = it.getIntExtra("position", -1)
            val departmentIndex = resources.getStringArray(R.array.status_array).indexOf(status)
            spinnerStatus.setSelection(departmentIndex)
        }

        btnSave.setOnClickListener {
            val name = editTextName.text.toString()
            val age = editTextAge.text.toString()
            val dept = editTextDept.text.toString()
            val status = spinnerStatus.selectedItem.toString()
            val resultIntent = Intent()

            resultIntent.putExtra("name", name)
            resultIntent.putExtra("age", age)
            resultIntent.putExtra("department", dept)
            resultIntent.putExtra("status", status)
            resultIntent.putExtra("position", position)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
